package com.honger1234.flowable.variable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 整体上来说，⽬前 流程 变量可以分为三种类型：
 * 1. 全局 流程 变量：在整个 流程 执⾏期间，这个 流程 变量都是有效的。
 * 2. 本地 流程 变量：这个只针对 流程 中某⼀个具体的 Task（ 任务 ）有效，这个 任务 执⾏
 * 完毕后，这个 流程 变量就失效了。
 * 3. 临时 流程 变量：顾名思义就是临时的，这个不会存⼊到数据库中。
 *
 */
@SpringBootTest
@Slf4j
public class GloableVariableTest {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    /**
     * 在流程启动设置流程变量
     */
    @Test
    void test01() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("days", 10);
        variables.put("reason", "休息⼀下");
        variables.put("startTime", new Date());
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave-noApprover", variables);
        log.info("id:{},activityId:{}", pi.getId(), pi.getActivityId());
    }

    /**
     * 通过 Task 设置变量
     */
    @Test
    void test04() {
        Task task = taskService.createTaskQuery().singleResult();
        //逐个设置
        taskService.setVariable(task.getId(), "taskDays", 10);
        //直接设置⼀个 Map
        Map<String, Object> variables = new HashMap<>();
        variables.put("taskReason", "休息⼀下");
        variables.put("taskStartTime", new Date());
        taskService.setVariables(task.getId(), variables);
    }

    /**
     * 完成任务时设置
     * 在完成⼀个 任务 的时候设置 流程 变量
     */
    @Test
    void test05() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables.put("completeTaskReason", "休息⼀下");
        variables.put("completeTaskStartTime", new Date());
        variables.put("completeTaskDays", 10);
        taskService.complete(task.getId(), variables);
    }

    /**
     * 通过流程设置
     */
    @Test
    void test06() {
//        Execution execution = runtimeService.createExecutionQuery().processDefinitionKey("leave-noApprover").singleResult();
        List<Execution> executions = runtimeService.createExecutionQuery().processDefinitionKey("leave-noApprover").list();
        if (executions != null && !executions.isEmpty()) {
            Execution execution = executions.get(0);
            runtimeService.setVariable(execution.getId(), "runtimeServiceDays", 10);
            Map<String, Object> variables = new HashMap<>();
            variables.put("runtimeServiceReason", "休息⼀下");
            variables.put("runtimeServiceStartTime", new Date());
            runtimeService.setVariables(execution.getId(), variables);
        }
    }

    /**
     * 从历史流程实例获取流程的所有变量
     */
    @Test
    public void test03() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave-noApprover").list();
        for (ProcessInstance processInstance : list) {
            List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId())
                    .list();
            for (HistoricVariableInstance variable : variables) {
                log.info("Name: {}, Value: {}", variable.getVariableName(), variable.getValue());
            }
        }
    }

    /**
     * 从正在进行的流程实例中获取流程的所有变量
     */
    @Test
    void test02() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave-noApprover").list();
        for (ProcessInstance processInstance : list) {
            Map<String, Object> variables = runtimeService.getVariables(processInstance.getId());
            Map<String, Object> variablesLocal = runtimeService.getVariablesLocal(processInstance.getId());
            for (String key : variables.keySet()) {
                Object value = variables.get(key);
                log.info("Variable name: {}, Value: {}", key, value);
            }
        }
    }


    /**
     * 删除流程的全部变量,包括流程变量和本地变量
     */
    @Test
    public void test07() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave-noApprover").list();
        for (ProcessInstance processInstance : list) {
            //processInstance.getId()表示要删除变量的流程实例ID，第二个参数null表示要删除所有的变量(错误，验证过null的时候不删除)
//            runtimeService.removeVariables(processInstance.getId(),null);

            Map<String, Object> variables = runtimeService.getVariables(processInstance.getId());
            List<String> objects = new ArrayList<>();
            for (String key : variables.keySet()) {
                objects.add(key);
                Object value = variables.get(key);
                log.info("Variable name: {}, Value: {}", key, value);
            }
            runtimeService.removeVariables(processInstance.getId(), objects);

        }

    }
}
