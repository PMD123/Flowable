package com.sap.sample.flowableServices;

import com.sap.sample.contentAssembly.ContentAssemblyContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class TriggerProcess {

    @GetMapping
    public String check(){
        return "App Started";
    }

    @GetMapping("hello")
    public String sayHello(@RequestParam("name") String name){
        return "Hello " + name;
    }

    @GetMapping("flowable")
    public void triggerProcess(){
        ContentAssemblyContainer oBJAssemblyContainer = new ContentAssemblyContainer();
        oBJAssemblyContainer.trigger();
    }

//    @GetMapping("process")
//    public  void findProcess(){
//        ContentAssemblyContainer obj = new ContentAssemblyContainer();
//        obj.find();
//    }
}
