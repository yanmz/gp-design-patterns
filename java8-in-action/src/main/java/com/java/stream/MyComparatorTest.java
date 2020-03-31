package com.java.stream;

import java.util.*;

/**
 * @author Tom
 */
public class MyComparatorTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao","hello","world","welcoe");
        Collections.sort(list);
        System.out.println(list);
        System.out.println("------------1---------------");

        //升序排列
        Collections.sort(list,(item1,item2)->item1.length()-item2.length());
        System.out.println(list);
        System.out.println("-------------2--------------");

        //降序排列
        Collections.sort(list,(item1,item2)->(item2.length()-item1.length()));
        System.out.println(list);
        System.out.println("-------------3--------------");

        //升序排列 调用reversed方法取反
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        System.out.println(list);
        System.out.println("-------------4--------------");

        //类型推断 由于reversed返回Comparator<T>类型，比较器Comparator.comparingInt()引用reversed的返回类型，
        //而(String item)->item.length()参数作为比较器的参数 从而无法推断出具体类型，编辑器只能推断出 ToIntFunction<? super T>类型的？是T类型的下层类型
        Collections.sort(list, Comparator.comparingInt((String item)->item.length()).reversed());
        System.out.println(list);
        System.out.println("-------------5--------------");

        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);
        System.out.println("--------------6-------------");

        list.sort(Comparator.comparingInt((String item)->item.length()).reversed());
        System.out.println(list);
        System.out.println("---------------7------------");

        //只有前面排序条件等于0时，thenComparing才能运行
        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);
        System.out.println("---------------8------------");

        list.sort(Comparator.comparingInt(String::length).thenComparing((item1,item2)->item1.toLowerCase().compareTo(item2.toLowerCase())));
        System.out.println(list);
        System.out.println("---------------9------------");

        list.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        System.out.println(list);
        System.out.println("---------------10------------");

        list.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        System.out.println(list);
        System.out.println("---------------11------------");

        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        System.out.println(list);
        System.out.println("---------------12------------");

        //最后一个thenComparing是不生效的
        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder()))
        .thenComparing(Comparator.reverseOrder()));
        System.out.println(list);
        System.out.println("---------------13------------");
    }
}
