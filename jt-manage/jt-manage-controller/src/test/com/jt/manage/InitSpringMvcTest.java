package com.jt.manage;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 测试时需要加载spring容器以及SpringMVC，直接继承该类即可
 *
 * @author liangzhongqiu789@sina.com
 * @date 2017-06-23
 * @time 11:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"/spring/applicationContext.xml", "/spring/springmvc-config.xml","/spring/applicationContext-redis.xml"})
public abstract class InitSpringMvcTest {

    @Autowired
    protected WebApplicationContext wac;

    protected HttpHeaders httpHeader;

    protected MockMvc mockMvc;

    @Before
    public void stepUp(){
        this.mockMvc = webAppContextSetup(wac).build();
    }

    protected void doRequest(String url, MediaType mediaType, Object object) throws Exception {
        MockHttpServletRequestBuilder builder;
        mediaType = mediaType == null ? MediaType.APPLICATION_FORM_URLENCODED : mediaType;
        if (mediaType == MediaType.MULTIPART_FORM_DATA) {
            builder = fileUpload(url);
        } else {
            builder = post(url);
        }
        builder.characterEncoding(StandardCharsets.UTF_8.name()).contentType(mediaType);
        if (object != null) {
            if (mediaType == MediaType.APPLICATION_FORM_URLENCODED) {
                if (object instanceof Map) {
                    Map<String, Object> params = (Map<String,Object>) object;
                    for (Map.Entry<String,Object> entry : params.entrySet()) {
                        builder.param(entry.getKey(), entry.getValue().toString());
                    }
                } else {
                    builder.content(object.toString());
                }
            } else if (mediaType == MediaType.APPLICATION_JSON) {
                String content;
                if (object instanceof String) {
                    content = object.toString();
                } else {
                    content = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
                }
                builder.content(content);
            } else if (mediaType == MediaType.MULTIPART_FORM_DATA) {

            } else {
                builder.content((byte[]) object);
            }
        }
        if(httpHeader != null){
            builder.headers(httpHeader);
        }
        mockMvc.perform(builder).andDo(print());
    }

}
