package com.java.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest7 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","word","hello word");
//        list.stream().map(item->item.substring(0,1).toUpperCase()+item.substring(1)).forEach(System.out::println);

        //中间流不会触发执行，只有终止操作才能执行
        //对于流的所有中间方法都是懒加载的或者延迟加载，如果没有遇到终止操作or及早求值就不会执行。
        list.stream().map(item->{
            String result = item.substring(0,1).toUpperCase()+item.substring(1);
            System.out.println("test");
            return result;
        }).forEach(System.out::println);
    }
}
