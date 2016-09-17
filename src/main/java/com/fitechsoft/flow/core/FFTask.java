package com.fitechsoft.flow.core;

import com.fitechsoft.domain.core.FDObject;
import org.activiti.engine.task.Task;

/**
 * Created by chun on 16/9/17.
 */
public class FFTask extends FDObject {

    private Task task;

    public FFTask(Task task){
        this.task = task;
    }
}
