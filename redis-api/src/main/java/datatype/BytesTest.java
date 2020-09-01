package datatype;

import org.apache.commons.io.FileUtils;
import util.JedisUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Tom
 */
public class BytesTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        File file = new File("E:\\微信图片_20200403162459.jpg");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String str = new String(bytes);
//        System.out.println(str);

        JedisUtil.getJedisUtil().hset("k", "mybytes", str);
        System.out.println(JedisUtil.getJedisUtil().hget("k", "mybytes"));
        JedisUtil jedisUtil = JedisUtil.getJedisUtil();
        System.out.println(jedisUtil.get("k1"));
    }
}
