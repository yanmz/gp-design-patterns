package com.java.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyTest1 {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "g", "8",null, "9");
//        String join = Joiner.on(":").join(list);
//        System.out.println(join);
        System.out.println("==================================");

        List<String> list1 = Lists.newArrayList("a", "b", "g", "8", null, "9");
//        String join1 = Joiner.on(",").skipNulls().join(list1);
//        System.out.println(join1);
        System.out.println("==================================");

        List<String> list2 = Lists.newArrayList("a", "b", "g", "8", null, "9");
        String join2 = Joiner.on(",").useForNull("11").join(list1);
        System.out.println(join2);
        System.out.println("==================================");

        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1, "哈哈");
        maps.put(2, "压压");
        String join3 = Joiner.on(",").withKeyValueSeparator(":").join(maps);
        System.out.println(join3);
        System.out.println("==================================");

        //splitter on 拆分
        String test = "34344,34,34,哈哈";
        List<String> lists = Splitter.on(",").splitToList(test);
        System.out.println(lists);
        System.out.println("==================================");

        // splitter trimResults 拆分去除前后空格
        String test1 = " 34344,34,34,哈哈";
        List<String> lists1 = Splitter.on(",").trimResults().splitToList(test1);
        System.out.println(lists1);
        System.out.println("==================================");

        //splitter omitEmptyStrings 去除拆分出来空的字符串
        String test2 = "  3434,434,34,,哈哈 ";
        List<String> lists2 = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(test2);
        System.out.println(lists2);
        System.out.println("==================================");


        //splitter fixedLength(int lenght) 把字符串按固定长度分割
        String test3 = "343443434哈哈";
        List<String> lists3 = Splitter.fixedLength(3).splitToList(test3);
        System.out.println(lists3);
        System.out.println("==================================");

        String str = "12312,agg";
        CharMatcher charMatcher = CharMatcher.is('g');
        System.out.println(charMatcher.retainFrom(str));
    }
}
