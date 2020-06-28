package com.monery;

import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟机栈: Stack  Frame 栈帧(如果局部变量表存放8个原生类型 那么原生类型的值也存放在局部变量表中，直接从里面取。如果存放的是引用类型，
 * 那么会通过指针或者句柄去堆查找指向的实体)
 * 程序计数器：
 * 本地方法栈：主要用于处理本地方法
 * 堆：JVM管理的最大一块内存，与堆相关的一个重要概念是垃圾收集器，现代几乎所有的垃圾收集器都是采用分代收集算法，所以堆空间也基于这一点进行相应的划分
 * 新生代 与老年代，Eden区 Form Survivor空间 To SURvivor
 * 方法区：储存元信息，永久代  从jdk1.8开始已经彻底废弃永久代 使用元空间
 * 运行时常量池；方法区的一部分
 * 直接内存：
 *
 * 关于Java对象的创建过程：
 * new关键字创建对象的3个步骤
 * 1.在堆内存中创建出对象的实例
 * 2.为对象的实例成员变量赋初始值
 * 3.将对象的引用返回
 *
 * 指针碰撞：（前提是堆中的空间通过一个指针进行分割，一侧是已经被占用的空间，另一侧是未被占用的空间）
 * 空闲列表(前提是堆内存空间中已被使用与未被使用空间交织在一起，这时，虚拟机就需要通过一个列表来记录哪些空间是可以使用的，
 * 哪些空间是已被使用的，接下来找出可以容纳下新创建对象的且未被使用的空间，在此空间存放该对象。同时还要修改列表上的记录)
 *
 * 对象在内存的布局
 * 1.对象头
 * 2.实例数据（即我们在一个类中所声明的各项信息）
 * 3.对齐填充
 *
 * 引用访问对象的方式：
 * 1.使用句柄的方式
 * 2.使用直接指针的方式
 */
public class MyTest1 {

    public static void main(String[] args) {
        List<MyTest1> list = new ArrayList<>();
        for (;;){
            list.add(new MyTest1());
            System.gc();
        }
    }
}
