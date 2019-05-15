package com.sap.sample.contentAssembly;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.identitylink.api.IdentityLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ContentAssemblyContainer {

    private static final Logger LOGGER = Logger.getLogger(ContentAssemblyContainer.class.getName());

    ProcessInstance objProcessInstance = null;
    ProcessEngine objProcEngine = null;

    public void trigger() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1").setJdbcUsername("sa").setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        objProcEngine = cfg.buildProcessEngine();
        LOGGER.info("Process Engine Name: " + objProcEngine.getName());
        deployDefinedProcess();

        find();

        /*startProcess();

        monitorProcess();*/
    }

    public void deployDefinedProcess() {

        RepositoryService repositoryService = objProcEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("contentassemblyprocess.bpmn20.xml").deploy();
        LOGGER.info("Deployment ID:" + deployment.getId());
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        if (processDefinition != null) {
            LOGGER.info("Found process definition Id : " + processDefinition.getId());
            LOGGER.info(" Name: " + processDefinition.getName());
        } else {
            LOGGER.info("Finding process Definition lead to an error/null");
        }
    }

    public void startProcess() {
        RuntimeService runtimeService = objProcEngine.getRuntimeService();
        Map<String, Object> objParameters = new HashMap<String, Object>();
        objParameters.put("URLSource",
                "https://www.flowable.org/docs/userguide/index.html#_starting_a_process_instance");
        objProcessInstance = runtimeService.startProcessInstanceByKey("contentAssembly", objParameters);
    }

    public void find() {
        RuntimeService runtimeService = objProcEngine.getRuntimeService();
        List<ProcessInstance> processes = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("contentAssembly")
                .list();
        LOGGER.info("findProcess: " + processes.toString());
    }

    public void monitorProcess() {

        ContentAssemblyProcessMonitor objMonitor = new ContentAssemblyProcessMonitor();
        objMonitor.objProcessInstance = objProcessInstance;

        Thread objThread = new Thread(objMonitor);
        objThread.run();

        LOGGER.info("STATUS: Content Assembly Process is Completed...");
    }
}
