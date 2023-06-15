package com.honger1234.flowable.variable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class TransientVariableTest {
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
     *临时流程变量
     * 临时 流程 变量是不存数据库的，⼀般来说我们可以在启动 流程 或者完成 任务 的时候使⽤
     *
     * 启动流程时传入临时流程变量
     *
     */
    @Test
    void test21() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息⼀下");
        variables.put("startTime", new Date());
        ProcessInstance pi = runtimeService
                .createProcessInstanceBuilder()
                .transientVariable("days", 10)
                .transientVariables(variables)
                .processDefinitionKey("leave-noApprover")
                .start();
        log.info("id:{},activityId:{}", pi.getId(),pi.getActivityId());
    }

    /**
     * 完成任务的时候设置临时变量
     */
    @Test
    void test22() {
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task : list) {
            Map<String, Object> transientVariables = new HashMap <> ();
            transientVariables.put("days", 10);
            taskService.complete(task.getId(), null, transientVariables);
        }
    }
}
