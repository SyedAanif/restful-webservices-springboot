package com.learnweb.restfulwebservices.controllers;

import com.learnweb.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
   // @Qualifier(value = "bundleMessageSource")
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "HELLO WORLD...";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HELLO WORLD...");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable(name = "name") String name){
        return new HelloWorldBean(String.format("HELLO WORLD, %s", name));
    }

    @GetMapping(path = "/hello-world-inter")
    public String helloWorldInter(){
        return messageSource.getMessage("good.morning.message" , null, LocaleContextHolder.getLocale());
    }
}
