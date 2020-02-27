package com.classloader;

/**
 *  对于数组实例来说，其类型是由jvm在运行期动态生成的，表示【Lcom.classloader.MyParent4;这种形势，动态生成的类型，其父类就是Object
 *  对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型
 *  助记符：
 *      anewarry：表示创建一个引用类型的（如类、接口、数组）数组，并将其引用值压入栈顶
 *      newarry: 表示创建一个指定的原始类型(如int、float、char)的数组，并将其引用值压入栈顶
 *
 */
public class MyTest4 {
    public static void main(String[] args) {
       MyParent4 myParent4 = new MyParent4();
        MyParent4[]  myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());
        MyParent4[][]  myParent4ss = new MyParent4[1][1];
        System.out.println(myParent4ss.getClass());

        int[] i = new int[1];
        System.out.println(i.getClass());//class [I
        System.out.println(i.getClass().getSuperclass());//class java.lang.Object

        char[] c =new char[1];
        System.out.println(c.getClass());//class [C
        System.out.println(c.getClass().getSuperclass());//class java.lang.Object
    }
}
class  MyParent4{
    {
        System.out.println("**MyParent4 static block...");
    }
    static {
        System.out.println("MyParent4 static block...");
    }
}
