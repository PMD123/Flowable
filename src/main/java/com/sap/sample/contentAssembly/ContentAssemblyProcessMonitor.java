package com.sap.sample.contentAssembly;

import org.flowable.engine.runtime.ProcessInstance;

import java.util.logging.Logger;

public class ContentAssemblyProcessMonitor implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ContentAssemblyProcessMonitor.class.getName());

    public ProcessInstance objProcessInstance = null;
    public Boolean monitor = true;

    public void run() {
        startMonitoring();
    }

    public void startMonitoring() {
        while (monitor) {
            try {
                Thread.sleep(300);
                if (objProcessInstance != null && !objProcessInstance.isEnded()) {
                    objProcessInstance.getStartTime();
                    LOGGER.info("Process is Running.!");
                } else {
                    LOGGER.info("waiting");
                    monitor = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
