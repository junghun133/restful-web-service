package com.pjh.restfulwebservice.user.controller;

import com.pjh.restfulwebservice.user.entity.User;
import com.pjh.restfulwebservice.user.service.UserDaoService;
import com.pjh.restfulwebservice.user.exception.UserNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /user1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) throws UserNotFoundException { // String -> id auto converting
        Optional<User> userOptional = service.findOne(id);
        User user = null;
        if(!userOptional.isPresent())
            throw new UserNotFoundException(String.format("[ID %d] not found", id));

        user = userOptional.get();
        //HATEOA
        Resource<User> resource = new Resource<>(user);
//        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        resource.add(controllerLinkBuilder.withRel("all-users"));
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(controllerLinkBuilder.withRel("all-users"));

        return resource;
    }

    //생성된 user id를 조회하는 API 생성하는 대신
    //response header에 포함되는 entity에 해당 user id 조회 location을 전달한다 = 불필요한 통신 제거
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public Object deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("[ID %d] not found", id));
        }
        return null;
    }
}
