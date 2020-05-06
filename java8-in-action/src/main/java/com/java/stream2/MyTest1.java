package com.java.stream2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class MyTest1 {
    public static void main(String[] args) {
        List<Object> list = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        map.put("new", new BigDecimal(0.2));
        map.put("parent", "30%");
        list.add(map);

////        Iterator parent = list.stream().map(i -> map.get("parent")).iterator();
//         String parent = list.stream().map(i -> i.get("parent"))
//                .map(i -> i.toString())
//                .collect(Collectors.joining(","));
//        System.out.println(parent);
//
//        final  String values = list.stream()
//                .map(item -> item.get("parent"))
//                .map(item -> item.toString())
//                .collect(Collectors.joining(","));
//        System.out.println(values);

        String parent = list.stream()
                .map(item -> JSON.parseObject(JSON.toJSONString(item)))
                .map(i -> i.get("parent")).map(i -> i.toString())
                .collect(Collectors.joining());
        System.out.println(parent);
    }
}

