package com.classloader;

import java.io.*;

public class MyTest16 extends ClassLoader {

    private String classLoaderName;

    private final String fileExtension=".class";

    public MyTest16(String classLoaderName){
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(String classLoaderName,ClassLoader parent){
        super(parent);
        this.classLoaderName = classLoaderName;
    }
    @Override
    public String toString(){
      return  "["+this.classLoaderName+"]";
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[]  data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    private byte[]  loadClassData(String  className)   {
        InputStream inputStream = null;
        byte[] b = null;
        ByteArrayOutputStream byteArrayOutputStream= null;
        try {
            this.classLoaderName = classLoaderName.replace(".","/");
            inputStream = new FileInputStream(new File(className+this.fileExtension));
            byteArrayOutputStream = new ByteArrayOutputStream();
            int h = 0;
            while(0!=(h = inputStream.read())){
                byteArrayOutputStream.write(h);
            }
            b = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                byteArrayOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return b;
    }
    public  static  void  test(ClassLoader classLoader) throws Exception {
        Class<?> aClass = classLoader.loadClass("com.classloader.MyTest1");
        Object o = aClass.newInstance();
        System.out.println(o);
    }

    public static void main(String[] args) throws Exception {
        MyTest16 myTest16 = new MyTest16("loader1");
        test(myTest16);
    }
}
