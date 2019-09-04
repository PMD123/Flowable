package com.sap.sample.contentAssembly;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("helloWorld")
public class HelloWorld { // implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());

//    public void execute(DelegateExecution execution){
//        LOGGER.info("Hello, this is working atleast");
//    }

    public void execute(){
        LOGGER.info("Hello, this is working atleast");
    }

}
