package com.honger1234.bootmybatisplusexample.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honger1234.bootmybatisplusexample.entity.Employee;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 员工测试类
 * @author: zt
 * @date: 2020年3月26日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Resource
    private IEmployeeDao employeeDao;

    @Test
    public void testSelectAll(){
        List<Employee> employees = employeeDao.selectList(null);
        System.out.println(employees);
    }

    @Test
    public void testInsert(){
        Employee employee=new Employee();
        employee.setLastName("啦啦啦");
        employee.setAge(66);
        employee.setEmail("183162");
        employee.setGender(0);

        int insert = employeeDao.insert(employee);
        System.out.println(employee.getId());
    }

    @Test
    public void testUpdateBiId(){
        Employee employee=new Employee();
        employee.setId(7);
        employee.setLastName("我要修改你");

        int i = employeeDao.updateById(employee);
        System.out.println(i);

    }

    @Test
    public void testSelectById(){
        Employee employee1 = employeeDao.selectById(7);
        System.out.println(employee1);
    }

    @Test
    public void testSelectOne(){
        Employee employee=new Employee();
        employee.setLastName("我要修改你");
        employee.setEmail("183162");

        //Employee employee1 = employeeDao.selectOne();
//        System.out.println(employee1);
    }

    @Test
    public void testSelectBatchIds(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(7);

        List<Employee> employees = employeeDao.selectBatchIds(integers);
        System.out.println(employees);
    }

    @Test
    public void testSelectMap(){
        HashMap objectHashMap = new HashMap<>();
        objectHashMap.put("last_name","啦啦啦");

        List list = employeeDao.selectByMap(objectHashMap);
        System.out.println(list);
    }

    @Test
    public void testSelectPage(){
        Page<Employee> employeePage = new Page<>(1,2);
        IPage<Employee> employeeIPage = employeeDao.selectPage(employeePage, null);

        System.out.println("总页数："+employeeIPage.getPages());
        System.out.println("总记录数："+employeeIPage.getTotal());
        List<Employee> records = employeeIPage.getRecords();
        System.out.println(records);

    }

    @Test
    public void testDeleteById(){
        int i = employeeDao.deleteById(6);
        System.out.println(i);
    }

    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("last_name","啦啦啦1");

        int i = employeeDao.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    public void testDeleteBatchIds(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(9);
        integers.add(10);
        integers.add(11);

        int i = employeeDao.deleteBatchIds(integers);
        System.out.println(i);
    }

    /**
     * 条件构造器 查询操作
     */
    @Test
    public void testEntityWrapperSelect(){
        //查询年龄在25-35岁，性别为1，姓名为Black的数据
        Page<Employee> employeePage = new Page<>(1,2);
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.between("age",22,35).eq("last_name","Black").eq("gender",1);
        IPage<Employee> employeeIPage = employeeDao.selectPage(employeePage, queryWrapper);
        System.out.println(employeeIPage.getRecords());
    }

    /**
     * condition查询
     */
    @Test
    public void testConditionSelect(){
        //查询年龄在25-35岁，性别为1，姓名为Black的数据
        Page<Employee> employeePage = new Page<>(1,2);
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        Integer minAge=25;
        Integer maxAge=35;
        String name="Black";
        queryWrapper.between(minAge>0&&maxAge>0,"age",minAge,maxAge).eq(StringUtils.isNotBlank(name),"last_name",name).eq("gender",1);
        IPage<Employee> employeeIPage = employeeDao.selectPage(employeePage, queryWrapper);
        System.out.println(employeeIPage.getRecords());
    }


}