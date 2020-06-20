package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author cuishuoguo
 * Created on 2019/9/27.
 */
@Slf4j
public class StrategyClassLoader {


    public static Map<String, Class> scan(String packageName, Map<String, Class> map) throws UnsupportedEncodingException {
        String[] split = packageName.split(",");
        for (String packName : split) {
            String replace = packName.replace(".", "/");
            log.error("扫描路径{}", replace);
            // 是否循环迭代
            boolean recursive = true;
            try {
                Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(replace);
                while (dirs.hasMoreElements()) {
                    URL url = dirs.nextElement();
                    //war包运行
                    if ("file".equals(url.getProtocol())) {
                        File file = new File(URLDecoder.decode(url.getFile(), "utf-8"));
                        if (!file.exists() || !file.isDirectory()) {
                            return null;
                        }
                        getClassMap(file.listFiles(), map, packName);
                    }
                    //jar包运行
                    if ("jar".equals(url.getProtocol())) {
                        //获取jar
                        JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(replace)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        // 添加到map
                                        Class loadClass = Class.forName(packageName + '.' + className);
                                        if (loadClass != null) {
                                            ApiType annotation = (ApiType) loadClass.getAnnotation(ApiType.class);
                                            if (annotation != null) {
                                                map.put(annotation.value(), loadClass);
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("扫描策略包出现错误{}", e.getMessage());
            }
        }
        return map;
    }

    public static void getClassMap(File[] files, Map<String, Class> map, String packageName) {
        for (File file : files) {
            if (file.isDirectory()) {
                getClassMap(file.listFiles(), map, packageName + "." + file.getName());
            } else {
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                Class loadClass = loadClass(packageName, className);
                if (loadClass != null) {
                    ApiType annotation = (ApiType) loadClass.getAnnotation(ApiType.class);
                    if (annotation != null) {
                        map.put(annotation.value(), loadClass);
                    }
                }

            }
        }

    }


    public static Class loadClass(String packageName, String className) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            log.error("load class error! clz: {}, e:{}", packageName + "." + className, e);
        }
        return null;
    }
}
