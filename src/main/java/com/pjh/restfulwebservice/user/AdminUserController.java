package com.pjh.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pjh.restfulwebservice.user.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
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
            id, name, joinDate, password, ssn, grade
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

    //@GetMapping("/v1/users/{id}")
    //@GetMapping(value = "/users/{id}/", params = "version=1")
    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) throws UserNotFoundException { // String -> id auto converting
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
    //@GetMapping("/v2/users/{id}") // VIP 기능 추가됨 (grade)
//    @GetMapping(value = "/users/{id}/", params = "version=2")
    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) throws UserNotFoundException { // String -> id auto converting
        Optional<User> userOptional = service.findOne(id);
        User user = null;
        if (userOptional.isEmpty())
            throw new UserNotFoundException(String.format("[ID %d] not found", id));
        user = userOptional.get();

        // User -> User2
        UserV2 userV2 = new UserV2();
        //Bean copy 가능한 class
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP"); //VIP 등급

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(
                        Field.name.id.name(),
                        Field.name.password.name(),
                        Field.name.name.name(),
                        Field.name.ssn.name(),
                        Field.name.grade.name()
                );
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userV2);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

