package com.honger1234.flowable.variable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 整体上来说，⽬前 流程 变量可以分为三种类型：
 * 1. 全局 流程 变量：在整个 流程 执⾏期间，这个 流程 变量都是有效的。
 * 2. 本地 流程 变量：这个只针对 流程 中某⼀个具体的 Task（ 任务 ）有效，这个 任务 执⾏
 * 完毕后，这个 流程 变量就失效了。
 * 3. 临时 流程 变量：顾名思义就是临时的，这个不会存⼊到数据库中。
 *
 *
 *
 * 以下是本地变量测试
 */
@SpringBootTest
@Slf4j
public class LocalVariableTest {
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
     * 本地流程变量
     * 通过 Task 设置,与task绑定
     */
    @Test
    void test08() {
        Task task = taskService.createTaskQuery().singleResult();
        taskService.setVariableLocal(task.getId(), "days", 10);
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息⼀下");
        variables.put("startTime", new Date());
        taskService.setVariables(task.getId(), variables);
    }

    /**
     * 完成Task后，这个Task的本地流程变量会自动删除
     */
    @Test
    void test10() {
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }

    /**
     * 通过Task查询本地流程变量
     */
    @Test
    void test09() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, Object> localVariables = taskService.getVariablesLocal(task.getId());
        for (String key : localVariables.keySet()) {
            Object value = localVariables.get(key);
            log.info("Variable name: {}, Value: {}", key, value);
        }
    }

}
