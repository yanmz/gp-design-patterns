package datatype;

import util.JedisUtil;

import java.util.List;

/**
 * @author Tom
 */
public class HashTest {
    public static void main(String[] args) {
        String str = JedisUtil.getJedisUtil().hget("h", "a");
        System.out.println(str);

        List<String> hmget = JedisUtil.getJedisUtil().hmget("h", "a", "b", "c", "d");
        System.out.println(hmget);
    }
}
