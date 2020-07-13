package com.bytecode;

import com.classloader.MyTest2;

/**
 * 栈帧
 * <p>
 * 栈帧是一种用于虚拟机执行方法调用与方法执行的数据结构。
 * 栈帧本身是一种数据结构，封装了方法的局部变量。动态链接信息、方法的返回地址以及操作数栈等信息
 * <p>
 * 有些符号引用是在类加载阶段或是第一次使用时就会转换为直接引用，这种转换叫做静态解析，另外一些符号引用则是在每次运行期转换为直接引用，
 * 这种转换叫做动态解析，这就体现为java的多态性。
 * <p>
 * 体现java多态性
 * Animal  a = new Dog();
 * a.sleep();
 * a= new Pig();
 * a.sleep();
 * 在虚拟机编译期间 不知道a真正指向dog 要到运行期才知道指向dog
 */

public class MyTest4 {

    public static void test() {
        System.out.println("test invoked");
    }

    public static void main(String[] args) {
        test();
    }
}
