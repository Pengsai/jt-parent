package com.jt.manage;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName test
 * @Description TODO
 * @Author PS
 * @Date 2018/6/20 9:48
 **/
public class test {

    @Test
    public void tst01() throws IOException {

        // 定义http请求对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 定义url
        String url = "http://news.cctv.com/2018/06/19/ARTI7m1bmZ5yNb9fg8XjTmnw180619.shtml";

        // 定义请求方式
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("sssssssssssss=========" + response.getEntity());
            String html = EntityUtils.toString(response.getEntity());
            System.out.println(html);
        }


    }


    @Test
    public void test02() {
        List<Student> allList = new ArrayList<>();
        //添加集合信息 省去。。。
        Student st1 = new Student();
        st1.setAge(20);
        st1.setHight(178L);
        st1.setSex(1);
        st1.setName("韩梅梅");
        allList.add(st1);
        Student st11 = new Student();
        st11.setAge(20);
        st11.setHight(168L);
        st11.setSex(1);
        st11.setName("马冬梅");
        allList.add(st11);

        Student st2 = new Student();
        st2.setAge(21);
        st2.setHight(179L);
        st2.setSex(2);
        st2.setName("李磊");
        allList.add(st2);
        Student st22 = new Student();
        st22.setAge(21);
        st22.setHight(189L);
        st22.setSex(2);
        st22.setName("小李");
        allList.add(st22);

        allList.parallelStream().filter((x) -> (x.getAge() < 21)).forEach(p-> System.out.println(p.toString()));



        // 以年龄 和 性别 分组，并选取最高身高的 学生
        Map<Long, Optional<Student>> allMapTask =
                allList.stream()
                        .collect(Collectors.groupingBy(Student::getIwantStudent, Collectors.maxBy(Comparator.comparing(Student::getHight))));



        // 遍历获取对象信息
        for (Map.Entry<Long, Optional<Student>> entry : allMapTask.entrySet()) {
            Student student = entry.getValue().get();
            System.out.println(entry.getKey());
            System.out.println(student.toString());
        }


    }
}
