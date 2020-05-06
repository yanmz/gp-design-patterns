package com.classloader;

import java.io.*;

public class  MyTest16 extends ClassLoader {

    private String classLoaderName;

    private String path;

    private final String fileExtension=".class";

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest16(String classLoaderName){
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent,String classLoaderName){
        super(parent);
        this.classLoaderName = classLoaderName;
    }
    @Override
    public String toString(){
      return  "["+this.classLoaderName+"]";
    }

    @Override
    protected Class<?> findClass(String name) {
        System.out.println("findClass invoked: "+name);
        System.out.println("class loader  name "+this.classLoaderName);
        byte[]  data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    private byte[]  loadClassData(String  className)   {
        InputStream inputStream = null;
        byte[] b = null;
        ByteArrayOutputStream byteArrayOutputStream= null;

        className =className.replace(".","\\");
        try {
            this.classLoaderName = classLoaderName.replace(".","/");
            inputStream = new FileInputStream(new File(this.path+className+this.fileExtension));
            byteArrayOutputStream = new ByteArrayOutputStream();
            int h = 0;
            while(-1 !=(h = inputStream.read())){
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

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
//        myTest16.setPath("E:\\workspace\\gp-design-patterns\\ssy-study-jvm\\target\\classes\\com\\classloader");
        loader1.setPath("C:\\Users\\Tom\\Desktop\\");
        Class<?> Class1 = loader1.loadClass("com.classloader.MyTest1");
        System.out.println(Class1.hashCode());
        Object obj = Class1.newInstance();
        System.out.println(obj);
        loader1 =null;
        Class1=null;
        obj = null;
        System.gc();
        Thread.sleep(1000000);

         loader1 = new MyTest16("loader1");
         loader1.setPath("C:\\Users\\Tom\\Desktop\\");
         Class1 = loader1.loadClass("com.classloader.MyTest1");
         System.out.println(Class1.hashCode());
         obj = Class1.newInstance();
         System.out.println(obj);


//        System.out.println("-------------------------");
//        MyTest16 loader2 = new MyTest16(loader1,"loader2");
//        loader2.setPath("C:\\Users\\Tom\\Desktop\\");
//        Class<?> Class2 = loader2.loadClass("com.classloader.MyTest1");
//        System.out.println(Class2.hashCode());
//        Object obj2 = Class2.newInstance();
//        System.out.println(obj2);
    }
}
