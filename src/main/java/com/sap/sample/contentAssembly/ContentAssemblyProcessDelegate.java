package com.sap.sample.contentAssembly;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.logging.Logger;

public class ContentAssemblyProcessDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger(ContentAssemblyProcessDelegate.class.getName());

    public void execute(DelegateExecution execution) {

        try{
            ContentFetchProcess objContentFetchProcess = new ContentFetchProcess();
            LOGGER.info("Process Delegate is invoking the actual process implementation...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            HashMap<String, String> objParameters = new HashMap<String, String>();
            objParameters.put("PROCESS_CONTENT_FETCH_URL", execution.getVariable("URLSource").toString());
            objContentFetchProcess.execute(objParameters);

            LOGGER.info("Process Delegate is Completed!.");
        }catch(Exception e){
            LOGGER.info("Process Delegate is Failed! : " + e.getMessage());
        }

    }

}
