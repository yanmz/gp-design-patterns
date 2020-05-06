package com.classloader;


/**
 对于数组，它对应的class对象不是由类加载器加载，而是由JVM在运行期动态的创建。然而对于数组类的类加载器来说，它返回的类加载器和数组内元素的类加载器是一样的。
 如果数组类元素是原生类，那么数组是没有类加载器的。
 */
public class MyTest27 {
    public static void main(String[] args){
        String[] strings=new String[2];
        System.out.println(strings.getClass());
        System.out.println(strings.getClass().getClassLoader());    //输出null

        MyTest27[] mytest15=new MyTest27[2];
        System.out.println(mytest15.getClass().getClassLoader());   //输出应用类加载器

        int[] arr=new int[2];
        System.out.println(arr.getClass().getClassLoader());        //输出null，此null非彼null
    }
}
