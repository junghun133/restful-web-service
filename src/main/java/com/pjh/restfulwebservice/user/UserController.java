package com.pjh.restfulwebservice.user;

import com.pjh.restfulwebservice.user.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public User retrieveUser(@PathVariable int id) throws UserNotFoundException { // String -> id auto converting
        Optional<User> userOptional = service.findOne(id);
        User user;
        try {
            user = userOptional.get();
        }catch (Exception e){
            throw new UserNotFoundException(String.format("[ID %d] not found", id));
        }
        return user;
    }

    //생성된 user id를 조회하는 API 생성하는 대신
    //response header에 포함되는 entity에 해당 user id 조회 location을 전달한다 = 불필요한 통신 제거
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
