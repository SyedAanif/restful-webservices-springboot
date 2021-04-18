package com.learnweb.restfulwebservices.controllers;

import com.learnweb.restfulwebservices.entity.User;
import com.learnweb.restfulwebservices.exception.UserNotFoundException;
import com.learnweb.restfulwebservices.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Api(value = "Controller for accessing User")
public class UserResources {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    @ApiOperation(value = "Retrieves all users",response = List.class)
    public List<User> retrieveAllUsers(){

        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable(name = "id") Integer id){

        User user = userService.findOne(id);

        EntityModel<User> resource=EntityModel.of(user);

        WebMvcLinkBuilder addedLink = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(addedLink.withRel("all-users"));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userToBeSaved){
        User savedUser = userService.save(userToBeSaved);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id){
        User deleteUser = userService.deleteUser(id);
        if(deleteUser==null){
            throw new UserNotFoundException(String.format("No User found with id:: %s", id));
        }
       return ResponseEntity.noContent().build();

    }
}
