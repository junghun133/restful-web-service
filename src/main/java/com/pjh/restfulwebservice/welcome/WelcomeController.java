package com.pjh.restfulwebservice.welcome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping(path = "/hi")
    public String hi(){
        return "Hello, i'm JungHoon-Park :)";
    }

    //RestController annotation을 사용하게 되면
    //java bean pojo 형태로 return되면 json 형태로 response 된다.
    @GetMapping(path = "/hi-bean")
    public HiBean hiBean(){
        return new HiBean("Hello, i'm JungHoon-Park :)");
    }

    @GetMapping(path = "/hi-bean/path-variable/{name}")
    public HiBean hiBean(@PathVariable(value = "name") String name){
        return new HiBean(String.format("Hello, i'm %s :)", name));
    }
}
