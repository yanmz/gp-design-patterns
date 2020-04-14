package com.gupaoedu.demo.spring.framework.servlet;

import java.io.File;

public class GPViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
    private File tempateRootDir;

    public GPViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        tempateRootDir = new File(templateRootPath);
    }


}
