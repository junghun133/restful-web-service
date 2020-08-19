package com.pjh.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pjh.restfulwebservice.user.exception.UserNotFoundException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 *  filter 연습
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    interface Field{
        enum name{
            id, name, joinDate, password, ssn
        }
    }
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> allUsers = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(
                        Field.name.id.name(),
                        Field.name.password.name(),
                        Field.name.name.name(),
                        Field.name.ssn.name()
                );
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsers);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    // GET /user1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) throws UserNotFoundException { // String -> id auto converting
        Optional<User> userOptional = service.findOne(id);
        User user = null;
        if (userOptional.isEmpty())
            throw new UserNotFoundException(String.format("[ID %d] not found", id));
        user = userOptional.get();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(
                        Field.name.id.name(),
                        Field.name.password.name(),
                        Field.name.name.name(),
                        Field.name.ssn.name()
                        );
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

