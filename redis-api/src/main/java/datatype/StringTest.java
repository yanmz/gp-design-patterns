package datatype;

import util.JedisUtil;

public class StringTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().set("xiaoyan", "2673");

        String str = JedisUtil.getJedisUtil().get("xiaoyan");
        System.out.println(str);
    }
}
