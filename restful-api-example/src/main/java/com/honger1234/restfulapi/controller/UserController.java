package com.honger1234.restfulapi.controller;

import com.honger1234.restfulapi.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description: RESTful API
 * @author: zt
 * @date: 2020年3月26日
 */

@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, UserEntity> users = Collections.synchronizedMap(new HashMap<Long, UserEntity>());
    static {
        //初始化map模拟数据库存储数据
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("RESTful API测试");
        user.setAge(20);
        users.put(user.getId(),user);
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<UserEntity> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<UserEntity> r = new ArrayList<UserEntity>(users.values());
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public UserEntity postUser(@ModelAttribute UserEntity user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return user;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public UserEntity getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public UserEntity putUser(@PathVariable Long id, @ModelAttribute UserEntity user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        UserEntity u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return u;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }
}
