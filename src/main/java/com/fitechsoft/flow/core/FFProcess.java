package com.fitechsoft.flow.core;

import com.fitechsoft.domain.core.FDObject;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by chun on 16/9/17.
 */
public abstract class FFProcess extends FDObject {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;


    public FFProcess(String processName, String processResource) {
        this.processName = processName;
        this.processResource = processResource;
    }

    public String getProcessResource() {
        return processResource;
    }

    public void setProcessResource(String processResource) {
        this.processResource = processResource;
    }

    private String processResource;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    private String processName;


    private String deploymentId;

    public void deploy() {
        this.deploymentId = repositoryService.createDeployment()
                .addClasspathResource(this.getProcessResource())
                .deploy()
                .getId();
    }


    public FFInstance startInstance(Map variables) {
        ProcessInstance instance =  runtimeService.startProcessInstanceByKey(processName, variables);
        return new FFInstance(instance);
    }


}
