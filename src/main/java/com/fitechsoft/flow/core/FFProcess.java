package com.fitechsoft.flow.core;

import com.fitechsoft.domain.core.FDObject;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by chun on 16/9/17.
 */
public abstract class FFProcess extends FDObject {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;


    public FFProcess(String processName, String processResource, String category) {
        this.processName = processName;
        this.processResource = processResource;
        this.category = category;
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

    public String getDeploymentId() {
        return deploymentId;
    }

    private String deploymentId;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public void deploy() {
        this.deploymentId = repositoryService.createDeployment()
                .addClasspathResource(this.getProcessResource())
                .category(getCategory())
                .deploy()
                .getId();
    }


    public FFInstance startInstance(Map variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processName, variables);
        logger.debug("发起了一个流程实例，processInstanceId = " + instance.getProcessInstanceId());
        return new FFInstance(instance);
    }

    public ProcessDefinition getProcessDefinition() {
        return repositoryService.getProcessDefinition(processName);
    }

    public List<ProcessInstance> getInstances() {
        return runtimeService.createProcessInstanceQuery().processDefinitionId(this.getDeploymentId()).list();
    }

}
