package com.gupaoedu.demo.spring.framework.servlet;

import com.gupaoedu.demo.spring.framework.annotation.GPController;
import com.gupaoedu.demo.spring.framework.annotation.GPRequestMapping;
import com.gupaoedu.demo.spring.framework.annotation.GPRequestParam;
import com.gupaoedu.demo.spring.framework.context.GPApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPMVCDispatchServlet extends HttpServlet {

    private GPApplicationContext applicationContext;

    Properties properties = new Properties();

    private List<GPHandlerMapping> handlerMappings = new ArrayList<GPHandlerMapping>();

    private Map<GPHandlerMapping,GPHandlerAdapter> handlerAdapters = new HashMap<GPHandlerMapping, GPHandlerAdapter>();

    private List<GPViewResolver> viewResolvers = new ArrayList<GPViewResolver>();

//    //IoC容器，key默认是类名首字母小写，value就是对应的实例对象
//    private Map<String,Object> ioc = new HashMap<String,Object>();

    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成了对HandlerMapping的封装
        //完成了对方法返回值的封装ModelAndView

        //1、通过URL获得一个HandlerMapping
        getHandler(req);

        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    //校验输入URL与 handlerMappings中的key-value（url-month）是否匹配
    private GPHandlerMapping getHandler(HttpServletRequest req) {
        if(this.handlerMappings.isEmpty()){return  null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        for (GPHandlerMapping mapping : handlerMappings) {
            Matcher matcher = mapping.getPattern().matcher(url);
            if(!matcher.matches()){continue;}
            return mapping;
        }
        return null;
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        Map<String,String[]> params = req.getParameterMap();

        Method method = this.handlerMapping.get(url);

        //获取形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        Object [] paramValues = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class paramterType = parameterTypes[i];
            if(paramterType == HttpServletRequest.class){
                paramValues[i] = req;
            }else if(paramterType == HttpServletResponse.class){
                paramValues[i] = resp;
            }else if(paramterType == String.class){
                //通过运行时的状态去拿到你
                Annotation[] [] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length ; j ++) {
                    for(Annotation a : pa[i]){
                        if(a instanceof GPRequestParam){
                            String paramName = ((GPRequestParam) a).value();
                            if(!"".equals(paramName.trim())){
                                String value = Arrays.toString(params.get(paramName))
                                        .replaceAll("\\[|\\]","")
                                        .replaceAll("\\s+",",");
                                paramValues[i] = value;
                            }
                        }
                    }
                }

            }
        }

        //暂时硬编码
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //赋值实参列表
        method.invoke(applicationContext.getBean(beanName),paramValues);
    }

    @Override
    public void init(ServletConfig config) {

        //初始化Spring核心IOC容器  完成了IoC、DI和MVC部分对接
        applicationContext = new GPApplicationContext(config.getInitParameter("contextConfigLocation"));

        //==============MVC部分==============
        //初始化九大组件
        initStrategies(applicationContext);
        System.out.println("GP Spring framework is init.");
    }

    private void initStrategies(GPApplicationContext context) {
        initHandlerMappings(context);

        //初始化参数适配器
        initHandlerAdapters(context);

        //初始化视图转换器
        initViewResolvers(context);
    }

    private void initViewResolvers(GPApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(templateRoot));
        }
    }

    //建立 handlerMapping与handlerAdapters 关联关系
    private void initHandlerAdapters(GPApplicationContext context) {
        for (GPHandlerMapping handlerMapping:handlerMappings){
            this.handlerAdapters.put(handlerMapping,new GPHandlerAdapter());
        }
    }

    private void initHandlerMappings(GPApplicationContext context) {
        if(this.applicationContext.getBeanDefinitionCount()==0){ return;}

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
            Object instance = this.applicationContext.getBean(beanName);

            Class<?> clazz =instance.getClass();

            if(!clazz.isAnnotationPresent(GPController.class)){ continue; }

            //相当于提取 class上配置的url
            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只获取public的方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                //提取每个方法上面配置的url
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);

                // //demo//query
                String regex = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);
                handlerMappings.add(new GPHandlerMapping(pattern,instance,method));
                System.out.println("Mapped : " + regex + "," + method);
            }
        }
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
                chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
