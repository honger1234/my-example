package com.honger1234.bootmapperexample.dao;

import com.honger1234.bootmapperexample.entity.Employee;
import com.honger1234.bootmapperexample.entity.UserEntity;
import com.honger1234.bootmapperexample.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 用户持久层测试类
 * @author: zt
 * @date: 2020年3月26日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private IUserService userService;

    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    public void testSelectOne(){
        UserEntity user=new UserEntity();
        user.setUserId(1);

        UserEntity userResult=userService.getSelectOne(user);

        System.out.println(userResult);
    }

    @Test
    public void testSelectByPrimaryKey(){
        int userId=1;
        UserEntity user=userService.getUserById(userId);
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        UserEntity userEntity = new UserEntity();
        //userEntity.setUserId(2);
        userEntity.setName("李四2");
        userEntity.setAge(20);
        userEntity.setSex("M");

        UserEntity user1=userService.save(userEntity);
        System.out.println(user1);
    }

    @Test
    public void testInsertSelective(){
        UserEntity userEntity = new UserEntity();
        //userEntity.setUserId(2);
        userEntity.setName("李四2");
        //userEntity.setAge(20);
        userEntity.setSex("M");

        UserEntity user=userService.saveUserSelective(userEntity);
        System.out.println(user);
    }

    @Test
    public void testUpdateByPrimaryKeySelective(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(4);
        userEntity.setName("王五");
        userEntity.setAge(28);

        UserEntity user=userService.updateByPrimaryKeySelective(userEntity);
        System.out.println(user);
    }

    @Test
    public void testDeleteByPrimaryKey(){

        int userId=3;
        boolean flag=userService.deleteUserById(userId);
        System.out.println(flag);
    }

    @Test
    public void testSelectByExample(){
        //目标age>20,sex为M或者age<20,sex为F
        //SELECT userId,name,age,sex FROM tb_user WHERE ( age > 20 and sex = M ) or ( age < 20 and sex = F )
        //1.创建example对象
        Example example = new Example(UserEntity.class);
        //2.通多examle创建Criteria对象
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        //3.通过Criteria对象封装查询条件
       // criteria1.andGreaterThan("age",20).andLessThan()
        criteria1.andGreaterThan("age",20).andEqualTo("sex","M");
        criteria2.andLessThan("age",20).andEqualTo("sex","F");

        //4.使用or关键词组装两个Criteria对象
        example.or(criteria2);

        //5.设置其它属性
        //设置排序信息
        example.orderBy("age").asc();

        //设置去重
        example.setDistinct(true);

        //设置select查询字段
        example.selectProperties("name","age");

        List<UserEntity> users=userService.getUserListByExample(example);
        System.out.println(users);
    }

    @Test
    public void testMapper(){
        List<Employee> employees = employeeMapper.selectAll();
        System.out.println(employees);
    }

    @Test
    public void testMyBatcchUpdate(){

        Employee employee1=new Employee();
        employee1.setEmpId(1);
        employee1.setEmpName("tom");
        employee1.setEmpAge(50);
        employee1.setEmpSalary(1254.37000);

        Employee employee2=new Employee();
        employee2.setEmpId(2);
        employee2.setEmpName("jerry");
        employee2.setEmpAge(50);
        employee2.setEmpSalary(6635.42000);

        List<Employee> employees=new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        employeeMapper.batchUpdate(employees);
//        System.out.println(i);
    }

}