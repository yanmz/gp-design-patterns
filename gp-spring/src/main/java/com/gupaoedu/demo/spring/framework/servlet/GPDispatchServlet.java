package com.gupaoedu.demo.spring.framework.servlet;

import com.gupaoedu.demo.spring.framework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatchServlet extends HttpServlet {

    Properties properties = new Properties();

    //享元模式，缓存
    private List<String> classNames = new ArrayList<String>();

    private Map<String, Object> ioc = new HashMap<String, Object>();

    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        Map<String, String[]> params = req.getParameterMap();

        Method method = this.handlerMapping.get(url);

        //获取形参列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class paramterType = parameterTypes[i];
            if (paramterType == HttpServletRequest.class) {
                paramValues[i] = req;
            } else if (paramterType == HttpServletResponse.class) {
                paramValues[i] = resp;
            } else if (paramterType == String.class) {
                //通过运行时的状态去拿到你
                Annotation[][] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length; j++) {
                    for (Annotation a : pa[i]) {
                        if (a instanceof GPRequestParam) {
                            String paramName = ((GPRequestParam) a).value();
                            if (!"".equals(paramName.trim())) {
                                String value = Arrays.toString(params.get(paramName))
                                        .replaceAll("\\[|\\]", "")
                                        .replaceAll("\\s+", ",");
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
        method.invoke(ioc.get(beanName), paramValues);
    }

    @Override
    public void init(ServletConfig config) {

        //加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、扫描相关的类
        doScanner(properties.getProperty("scanPackage"));

        //==============IoC部分==============
        //3、初始化IoC容器，将扫描到的相关的类实例化，保存到IcC容器中
        doInstance();

        //==============DI部分==============
        //4、完成依赖注入
        doAutowired();

        //==============MVC部分==============
        //5、初始化HandlerMapping
        doInitHandlerMapping();
    }

    private void doInitHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(GPController.class)) {
                continue;
            }

            //相当于提取 class上配置的url
            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只获取public的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                    continue;
                }
                //提取每个方法上面配置的url
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);

                // //demo//query
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped : " + url + "," + method);
            }
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(GPAutowired.class)) {
                    continue;
                }
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                //如果用户没有自定义的beanName，就默认根据类型注入
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                //暴力访问
                field.setAccessible(true);

                try {
                    //ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(GPController.class)) {

                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    //1、在多个包下出现相同的类名，只能寄几（自己）起一个全局唯一的名字
                    //自定义命名
                    String beanName = clazz.getAnnotation(GPService.class).value();
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    //2、默认的类名首字母小写
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    //3、如果是接口
                    //判断有多少个实现类，如果只有一个，默认就选择这个实现类
                    //如果有多个，只能抛异常
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("The " + i.getName() + " is exists!!");
                        }
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {

        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replaceAll(".class", ""));
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
