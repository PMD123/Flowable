package com.sap.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sap.sample")
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class,args);
    }

}
