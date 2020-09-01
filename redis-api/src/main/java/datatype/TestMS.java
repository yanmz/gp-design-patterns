package datatype;

import redis.clients.jedis.Jedis;

/**
 * 主动复制
 * 命令：info replication 查看当前机器是master 还是salve
 * 命令：slaveof 127.0.0.1  6379 将当前机器设置为端口为6379的从机器
 * @author yanmz
 * @version 1.0
 * @date 2020/8/23 17:50
 */
public class TestMS {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("127.0.0.1",6379);
        Jedis jedis_S = new Jedis("127.0.0.1",6380);

        jedis_S.slaveof("127.0.0.1",6379);

        jedis_M.set("class","11112222");

        System.out.println(jedis_S.get("class"));
    }
}
