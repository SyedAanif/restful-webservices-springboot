package com.learnweb.restfulwebservices.controllers;

import com.learnweb.restfulwebservices.model.Name;
import com.learnweb.restfulwebservices.model.PersonV1;
import com.learnweb.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping(path = "v1/person")
    public PersonV1 personV1(){
        return new PersonV1("BOB CHARLIE");
    }

    @GetMapping(path = "v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Jack", "Sparrow"));
    }

    @GetMapping(path = "person",params = {"version=1"})
    public PersonV1 paramV1(){
        return new PersonV1("BOB CHARLIE");
    }

    @GetMapping(path = "person",params = {"version=2"})
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Jack", "Sparrow"));
    }

    @GetMapping(path = "person",headers = {"X-API-VERSION=1"})
    public PersonV1 headerV1(){
        return new PersonV1("BOB CHARLIE");
    }

    @GetMapping(path = "person",headers = {"X-API-VERSION=2"})
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Jack", "Sparrow"));
    }

    @GetMapping(path = "person",produces ={"application/vnd.company.app-v1+json"})
    public PersonV1 producesV1(){
        return new PersonV1("BOB CHARLIE");
    }

    @GetMapping(path = "person",produces ={"application/vnd.company.app-v2+json"})
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Jack", "Sparrow"));
    }
}
