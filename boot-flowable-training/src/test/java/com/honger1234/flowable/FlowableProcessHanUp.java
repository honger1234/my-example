package com.honger1234.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程的挂起与激活
 * 流程的挂起激活分两类
 * 1. 流程 定义的挂起和激活。
 * 2. 流程 实例的挂起和激活。
 * ⼀个定义好的流程 ，如果挂起了，那么就⽆法据此创建新的 流程 。
 * ⼀个 流程 实例如果挂起了，那么就⽆法执⾏ 流程 中的 任务 。
 *
 */
@SpringBootTest
@Slf4j
public class FlowableProcessHanUp {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     *查询流程定义是否挂起
     */
    @Test
    void test01() {
        List<ProcessDefinition> list =repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            String id = processDefinition.getId();
            boolean suspended =repositoryService.isProcessDefinitionSuspended(id);
            if (suspended) {
                log.info("流程定义 {} 已挂起",processDefinition.getName());
            }else{
                log.info("流程定义 {} 未挂起",processDefinition.getName());
            }
        }
    }

    /**
     * 将所有流程定义挂起
     */
    @Test
    void testSuspended() {
        List<ProcessDefinition> list =
                repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.suspendProcessDefinitionById(pd.getId());
        }
    }

    /**
     * 激活所有的流程定义
     */
    @Test
    void testActivateProcess() {
        List<ProcessDefinition> list =repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.activateProcessDefinitionById(pd.getId());
        }
    }

    /**
     * 根据key启动流程，也就是将流程定义实例化
     * 如果要启动的流程定义被挂起则会报错
     */
    @Test
    void testStartProcess() {
        identityService.setAuthenticatedUserId("赵1");
        Map<String, Object> variables = new HashMap<>();
        variables.put("approve", "赵2");
        ProcessInstance pi =runtimeService.startProcessInstanceByKey("leave",variables);
        log.info("id:{},activityId:{}", pi.getId(),pi.getActivityId());
    }

    /**
     * 流程实例的挂起
     * 流程定义挂起和流程实例挂起都是同一个方法，只不过实例挂起多了两个参数
     * 1. 第⼆个参数 true 表⽰是否要挂起这个 流程 定义对应的所有的 流程 实例，true 表⽰
     * 挂起。
     * 2. 第三个参数 null 表⽰ 流程 挂起的具体时间，如果该参数为 null，则 流程 会被⽴⻢
     * 挂起，如果该参数是⼀个具体的⽇期，则到期之后 流程 才会被挂起，但是这个需要 job
     * executor 的⽀持。
     *
     *
     * 流程实例挂起结果：
     * 1. 流程 的执⾏实例被挂起。
     * 2. 流程 的 Task 被挂起。
     * 3.流程定义本⾝也被挂起了
     */
    @Test
    void testSuspendProcessInstance() {
        List<ProcessDefinition> list =repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.suspendProcessDefinitionById(pd.getId(),true, null);
        }
    }

    /**
     * 激活流程定义和流程实例
     * activateProcessDefinitionById参数说明：
     * 1. 流程 定义的 ID。
     * 2. 是否激活 流程 定义对应的 流程 实例。
     * 3. 激活 流程 实例的时间，null 表⽰⽴⻢激活，如果是⼀个具体的时间，则到期激活，不
     * 过和之前的⼀样，这⾥也需要 job executor 的⽀持。
     *
     *
     */
    @Test
    void testActivateProcessInstance() {
        List<ProcessDefinition> list =repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.activateProcessDefinitionById(pd.getId(),true, null);
        }
    }

    /**
     * 只挂起流程实例而不是流程实例和流程定义一起挂起
     */
    @Test
    public void testSuspendAndResumeProcessInstance() {
        // 查询所有的流程实例
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance processInstance : list) {
            // 挂起流程实例
            runtimeService.suspendProcessInstanceById(processInstance.getId());
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            log.info("id:{},activityId:{},suspended:{},流程发起人:{}",processInstance.getId(),processInstance.getActivityId(),task.isSuspended(),processInstance.getStartUserId());
        }
    }

    /**
     *  只激活流程实例
     */
    @Test
    public void testResumeProcessInstance(){
        // 查询所有的流程实例
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance processInstance : list) {
            // 恢复流程实例
            runtimeService.activateProcessInstanceById(processInstance.getId());
            // 获取流程当前任务
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            log.info("id:{},activityId:{},suspended:{},流程发起人:{}",processInstance.getId(),processInstance.getActivityId(),task.isSuspended(),processInstance.getStartUserId());
        }
    }


    /**
     * 审批wangwu负责的流程，流转到下一个节点
     */
    @Test
    void testTask() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("李四").list();
        Map<String, Object> variables = new HashMap<>();
        variables.put("approve", "李五");
        for (Task task : list) {
            taskService.complete(task.getId(),variables);
        }
    }


    /**
     * 1.能不能根据key指定挂起和激活流程（可以）
     * 2.能不能单独只挂起流程的实例而不挂起流程定义（可以）
     *
     */



}
