package com.myboot.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.myboot.demo.config.NeoProperties;
import com.myboot.demo.controller.HelloController;
import com.myboot.demo.daoAbstract.CustomerDao;
import com.myboot.demo.domain.User;
import com.myboot.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ComponentScan(basePackages={"com.myboot.demo.daoAbstract.impl"})

//@WebMvcTest(HelloController.class)
//https://stackoverflow.com/questions/39865596/difference-between-using-mockmvc-with-springboottest-and-using-webmvctest
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserMapper userMapper;
    	
    @MockBean
	NeoProperties neoProperties;
    
    @Autowired
    CustomerDao customerDao;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(document("hello"));
    }
    
    @Test
    public void shouldReturnUser() throws Exception {
    	User user=new User();
    	user.setId(1);
    	user.setUserName("张三");
		when(userMapper.get(1)).thenReturn(user);
		this.mockMvc.perform(get("/getUser/{id}",1)).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("\"userName\":\"张三\"")))
        .andDo(document("getUser",pathParameters(
        		 parameterWithName("id").description("user id")
        		)
         ));
	}
    
    @Test
    public void adocBuild() throws IOException {
        String appDir = System.getProperty("user.dir");
        String adocPath = appDir + "\\src\\main\\asciidoc\\index.adoc";
        StringBuilder content = new StringBuilder();
        content.append("include::" + appDir + "\\src\\main\\asciidoc\\preview.adoc[]" + "\n\n");

        Files.list(Paths.get(appDir + "\\target\\snippets")).forEach(f -> {
            String apiName = f.getFileName().toString();
            content.append("=== " + apiName + "\n\n");
            fileAppend(content, f + "\\request-headers.adoc", "request-headers 类型说明");
            fileAppend(content, f + "\\http-request.adoc", "http-request");
            fileAppend(content, f + "\\request-fields.adoc", "request-fields 类型说明");
            fileAppend(content, f + "\\path-parameters.adoc", "path-parameters类型说明");
            fileAppend(content, f + "\\request-parameters.adoc", "request-parameters类型说明");
            fileAppend(content, f + "\\request-body.adoc", "request-body类型说明");
            fileAppend(content, f + "\\http-response.adoc", "http-response");
            fileAppend(content, f + "\\response-fields.adoc", "response-fields 类型说明");
        });
        Files.write(Paths.get(adocPath), content.toString().getBytes("UTF-8"), StandardOpenOption.CREATE);
    }
    
    private void fileAppend(StringBuilder stringBuilder, String path, String title) {
        if (Files.exists(Paths.get(path))) {
            stringBuilder.append("==== " + title + " \n\n");
            stringBuilder.append("include::" + path + "[]" + "\n\n");
        }
    }
}
