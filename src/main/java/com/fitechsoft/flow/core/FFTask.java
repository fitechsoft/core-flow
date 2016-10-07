package com.fitechsoft.flow.core;

import com.fitechsoft.domain.core.FDObject;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chun on 16/9/17.
 */
public class FFTask extends FDObject {

    @Autowired
    static TaskService taskService;

    public Task getTask() {
        return task;
    }

    private Task task;

    public FFTask(Task task) {
        this.task = task;
    }

    private URL handleEntry;
    private URL completeEntry;

    public URL getHandleEntry() {
        return handleEntry;
    }

    public URL getCompleteEntry() {
        return completeEntry;
    }

    public static List<FFTask> getToDoTasksForUser(String procDefId, String user) {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionId(procDefId).taskCandidateOrAssigned(user).list();
        List<FFTask> ffTasks = new ArrayList<>();

        tasks.forEach((task) -> ffTasks.add(new FFTask(task)));

        return ffTasks;
    }

    public static FFTask getTask(String task){
        return new FFTask(taskService.createTaskQuery().taskDefinitionKey(task).list().get(0));
    }

    public void complete(){
        taskService.complete(task.getId());
    }


}
