package com.java.guava;

import com.google.common.collect.Lists;
import com.java.action.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyTest2 {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("b1", "a1");
//        map.put("b2", "a2");
//        map.put("b3", "a1");
//        Map<String, List<String>> mm = map.values().stream().collect(Collectors.groupingBy(m -> m));
//        System.out.println(mm);

        List<Entity> list = new ArrayList<Entity>();

        list.add(Entity.builder().k("1").v("z").build());
        list.add(Entity.builder().k("1").v("x").build());
        list.add(Entity.builder().k("1").v("c").build());
        list.add(Entity.builder().k("2").v("a").build());
        list.add(Entity.builder().k("2").v("s").build());
        list.add(Entity.builder().k("3").v("q").build());
        Map<String, List<String>> stringListMap = toMap(list);



        Map<String, List<String>> namesByCity =
                list.stream().collect(Collectors.groupingBy(Entity::getK,Collectors.mapping(Entity::getV, Collectors.toList())));
        System.out.println(namesByCity);
    }

    private static Map<String , List<String>> toMap(List<Entity> list) {
        final Map<String , List<String>> map = new HashMap<>();

        list.forEach(item -> {
            String key = item.getK();
            String value = item.getV();

            List<String> keyList = map.get(key);
            if(null == keyList) {
                keyList = new ArrayList<String>();
                map.put(key , keyList);
            }
            keyList.add(value);
        });

        return map;
    }
}
