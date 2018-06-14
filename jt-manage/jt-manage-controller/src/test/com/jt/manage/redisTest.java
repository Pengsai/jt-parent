package com.jt.manage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName redisTest
 * @Description TODO
 * @Author PS
 * @Date 2018/6/13 21:12
 **/
public class redisTest extends InitSpringMvcTest {






    @Test
    public void test(){

        //String result = jedisCluster.set("v2", "k2");
       // String v1 = jedisCluster.get("v1");
        //System.out.println(result);
      //  System.out.println(v1);
    }



    /*public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.254.128",6379);
        String result = jedis.set("1707", "毕业论文");
        System.out.println(result);
        String data = jedis.get("1707");
        System.out.println(data);
    }*/
}
