package com.design.factory.protype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom
 */
public class ColneTest {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setName("小颜");
        List<String> list = new ArrayList();
        list.add("跳舞");
        list.add("看书");
        list.add("打球");
        user.setHobiies(list);


        User user1 = new CloneUser().deepClone(user);
        user1.getHobiies().add("跑步");
        System.out.println("克隆对象: " + user1);
        System.out.println("原型对象: " + user);
    }
}
