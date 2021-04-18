package com.learnweb.restfulwebservices.controllers;

import com.learnweb.restfulwebservices.entity.Post;
import com.learnweb.restfulwebservices.entity.User;
import com.learnweb.restfulwebservices.exception.UserNotFoundException;
import com.learnweb.restfulwebservices.repository.PostRepository;
import com.learnweb.restfulwebservices.repository.UserRepository;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Api(value = "JPA Controller for accessing User")
@RequestMapping(path = "/jpa")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/users")
    @ApiOperation(value = "Retrieves all users",response = List.class)
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable(name = "id") Integer id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No user found with id:: %s", id)));

        EntityModel<User> resource=EntityModel.of(user);

        WebMvcLinkBuilder addedLink = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(addedLink.withRel("all-users"));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userToBeSaved){
        User savedUser = userRepository.save(userToBeSaved);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id){
        userRepository.deleteById(id);

       return ResponseEntity.noContent().build();

    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getAllPosts(@PathVariable(name = "id") Integer id){
        return postRepository.findByUserId(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<User> createPost(@PathVariable(name = "id") Integer id,
                                           @RequestBody Post postToBeSaved){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No user found with id:: %s", id)));
        postToBeSaved.setUser(user);
        postRepository.save(postToBeSaved);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postToBeSaved.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }
}
