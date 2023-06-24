package com.honger1234.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class FlowableProcessTest {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;

    /**
     * 启动多个流程
     * 每启动一个流程都会生成两份数据，一份是正在进行的流程表数据和历史流程表数据
     * 正在进行的流程数据可以直接删除，但历史流程删除有限制，如果是正在进行的流程就不能删除历史流程
     */
    @Test
    public void testAdd(){
        //下⼀个节点的处理⼈,也就是设置“提交请假申请”这一节点的处理人
        Map<String, Object> variables = new HashMap<>();
        variables.put("approve", "张三1");
        //设置 流程 发起⼈
        Authentication.setAuthenticatedUserId("张");
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("leave",variables);
        log.info("id:{},activityId:{},ended:{}",leave.getId(),leave.getActivityId(),leave.isEnded());
        variables.put("approve", "张三2");
        ProcessInstance leave1 = runtimeService.startProcessInstanceByKey("leave",variables);
        log.info("id:{},activityId:{},ended:{}",leave1.getId(),leave1.getActivityId(),leave1.isEnded());
        variables.put("approve", "张三3");
        //设置 流程 发起⼈
        Authentication.setAuthenticatedUserId("王");
        ProcessInstance leave2 = runtimeService.startProcessInstanceByKey("leave",variables);
        log.info("id:{},activityId:{},ended:{}",leave2.getId(),leave2.getActivityId(),leave2.isEnded());
    }

    /**
     * 查询正在进行的所有流程
     */
    @Test
    public void testAll(){
        // 查询所有流程实例
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> processInstances = processInstanceQuery.list();
        for (ProcessInstance processInstance : processInstances) {
            log.info("id:{},activityId:{}",processInstance.getId(),processInstance.getActivityId());
            // 查询流程的发起人
            HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstance.getId());
            HistoricProcessInstance historicProcessInstance = query.singleResult();
            log.info("流程的发起人：{}",historicProcessInstance.getStartUserId());
        }
    }

    /**
     * 根据流程发起人查询历史流程
     */
    @Test
    public void testProcessByStartedBy(){
        // 根据发起人查询流程
        String initiator = "张";
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .startedBy(initiator);
        List<HistoricProcessInstance> processInstances = query.list();
        for (HistoricProcessInstance processInstance : processInstances) {
            log.info("id:{},流程发起人:{}",processInstance.getId(),processInstance.getStartUserId());
        }
    }

    /**
     * 根据发起人查询正在进行的流程
     */
    @Test
    public void testProcessByStartedBy2(){
        // 根据发起人查询流程实例
        String initiator = "张";
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery()
                .startedBy(initiator);
        List<ProcessInstance> processInstances = query.list();
        for (ProcessInstance processInstance : processInstances) {
            log.info("id:{},流程发起人:{}",processInstance.getId(),processInstance.getStartUserId());
        }
    }

    /**
     * 根据发起人查询已完成的流程实例
     */
    @Test
    public void testProcessByStartedBy3(){
        String initiator = "张";
        // 根据发起人查询已完成的流程实例
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .startedBy(initiator)
                .finished();
        List<HistoricProcessInstance> historicProcessInstances = query.list();
        for (HistoricProcessInstance processInstance : historicProcessInstances) {
            log.info("id:{},流程发起人:{}",processInstance.getId(),processInstance.getStartUserId());
        }
    }

    /**
     * 删除所有正在进行的流程
     */
    @Test
    public void testDeleteAll(){
        // 查询所有流程实例
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> processInstances = processInstanceQuery.list();
        for (ProcessInstance processInstance : processInstances) {
            log.info("id:{},activityId:{}",processInstance.getId(),processInstance.getActivityId());
            runtimeService.deleteProcessInstance(processInstance.getId(), "Test deletion");
        }
    }

    /**
     * 删除所有历史流程(历史流程：已完成的流程和runtimeService.deleteProcessInstance删除的流程)
     * 正在进行的流程不能删除，会抛出异常
     */
    @Test
    public void testDeleteAllHistory(){
        // 查询所有流程实例
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        List<HistoricProcessInstance> list = historicProcessInstanceQuery.list();
        for (HistoricProcessInstance processInstance : list) {
            log.info("id:{},发起人:{}",processInstance.getId(),processInstance.getStartUserId());
            historyService.deleteHistoricProcessInstance(processInstance.getId());
        }
    }

    /**
     * 根据节点负责人查询正在进行的流程
     */
    @Test
    public  void testProcessByApprover(){
        // 根据节点负责人查询流程
        String assignee = "张三1";
        TaskQuery query = taskService.createTaskQuery().taskAssignee(assignee);
        List<Task> tasks = query.list();
        for (Task task : tasks) {
            log.info("id:{}；name:{}；taskDefinitionKey:{}",task.getId(),task.getName(),task.getTaskDefinitionKey());
        }
    }

    /**
     * 根据节点负责人查询历史流程
     */
    @Test
    public  void testHistoryProcessByApprover(){
        // 根据节点负责人查询流程
        String assignee = "张三1";
        // 查询历史任务实例
        HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee);
        List<HistoricTaskInstance> historicTaskInstances = taskQuery.list();

        // 获取历史流程实例ID列表
        Set<String> processInstanceIds = historicTaskInstances.stream()
                .map(HistoricTaskInstance::getProcessInstanceId)
                .distinct()
                .collect(Collectors.toSet());

        // 查询历史流程实例
        HistoricProcessInstanceQuery processQuery = historyService.createHistoricProcessInstanceQuery()
                .processInstanceIds(processInstanceIds);
        List<HistoricProcessInstance> historicProcessInstances = processQuery.list();
        for (HistoricProcessInstance task : historicProcessInstances) {
            log.info("id:{}；name:{}；processDefinitionName:{}",task.getId(),task.getName(),task.getProcessDefinitionName());
        }
    }



}
