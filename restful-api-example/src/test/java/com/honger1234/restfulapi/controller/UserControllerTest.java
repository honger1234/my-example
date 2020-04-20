package com.honger1234.restfulapi.controller;

import com.honger1234.restfulapi.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
                new UserController()).build();
    }

    @Test
    void getUserList() throws Exception {
        // 1、get查一下user列表，应该有一条数据
        RequestBuilder request = get("/users/");
        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isOk());
//                .andExpect(content().string(equalTo("[]")));
        //将返回的信息做utf-8处理防止中文乱码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加输出
        resultActions.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void postUser() throws Exception {
        // 2、post提交一个user
        RequestBuilder request = post("/users")
                .param("id", "2")
                .param("name", "测试大师")
                .param("age", "20");
        ResultActions resultActions = mvc.perform(request)
                //.andDo(print()) 没做utf-8处理在这里打印可能出现中文乱码
//                .andDo(print())
//                .andExpect(content().string(equalTo("success")))
               ;
        //将返回的信息做utf-8处理防止中文乱码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加输出
        resultActions.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {

        // 3、获取一个id为1的user
        RequestBuilder request = get("/users/1");
        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isOk());
//                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        //将返回的信息做utf-8处理防止中文乱码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加输出
        resultActions.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void putUser() throws Exception {
        // 4、put修改id为1的user
        UserEntity user = new UserEntity();
        user.setId(3L);
        user.setName("更新API");
        user.setAge(30);
        RequestBuilder request = put("/users/1")
                .param("user", user.toString());
        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isOk());
        //将返回的信息做utf-8处理防止中文乱码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加输出
        resultActions.andDo(print()).andExpect(status().isOk());

    }

    @Test
    void deleteUser() throws Exception {
        // 5、del删除id为1的user
        RequestBuilder request = delete("/users/1");
        ResultActions resultActions = mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
        //将返回的信息做utf-8处理防止中文乱码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加输出
        resultActions.andDo(print()).andExpect(status().isOk());
    }
}