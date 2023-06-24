package com.honger1234.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.runtime.DataObject;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

/**
 * flowable中的DataObject测试类
 * DataObject中的数据类型string、boolean、datatime、double、int、long
 */
@SpringBootTest
@Slf4j
public class FlowableProcessDataObjectTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessEngine processEngine;


    @Test
    public void aVoid(){

        //启动流程
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave2");
        //设置dataobject并启动流程
        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder().variable("myName", "测试DataObject").processDefinitionKey("leave2").start();
        List<Execution> list =runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
        for (Execution execution : list) {
            DataObject data =runtimeService.getDataObject(execution.getId(), "流程绘制⼈");
            if (Objects.nonNull(data)){
                log.info("key:{},name:{},value:{}",data.getDataObjectDefinitionKey(),data.getName(),data.getValue());
            }
        }
    }

    @Test
    public void aVoid2(){

        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave-dataObject");
        log.info(processInstance.getId());
        //设置dataobject并启动流程
//        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder().variable("myName", "测试DataObject").processDefinitionKey("leave2").start();
//        List<Execution> list =runtimeService.createExecutionQuery().list();
//        for (Execution execution : list) {
////            Map<String, DataObject> dataObjects = runtimeService.getDataObjects(execution.getId());
////            log.info("name:{},date:{},dataObjects:{}",dataObjects.get("name"),dataObjects.get("data"),dataObjects);
//            DataObject data =runtimeService.getDataObject(execution.getId(), "name");
//            if (Objects.nonNull(data)){
//                log.info("key:{},name:{},value:{}",data.getDataObjectDefinitionKey(),data.getName(),data.getValue());
//            }
//        }
    }
}
