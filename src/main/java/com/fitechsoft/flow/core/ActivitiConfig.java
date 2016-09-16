package com.fitechsoft.flow.core;

import com.fitechsoft.repository.core.InfrastructureConfig;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chun on 16/9/16.
 */
@Configuration
public class ActivitiConfig extends InfrastructureConfig {


    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {

        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource());
        processEngineConfiguration.setTransactionManager(transactionManager());
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setMailServerPort(1025);

        return processEngineConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        ProcessEngineFactoryBean engineFactoryBean = new ProcessEngineFactoryBean();
        engineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());
        return engineFactoryBean;
    }

    @Bean
    public IdentityService identityService() throws Exception {
        return processEngine().getObject().getIdentityService();
    }

    @Bean
    public FormService formService() throws Exception {
        return processEngine().getObject().getFormService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getObject().getHistoryService();
    }

    @Bean
    public ManagementService managementService() throws Exception {
        return processEngine().getObject().getManagementService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public ResumeService resumeService(){
        return new ResumeService();
    }
}
