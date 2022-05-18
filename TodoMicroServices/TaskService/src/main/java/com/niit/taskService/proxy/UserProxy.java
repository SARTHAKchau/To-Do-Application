package com.niit.taskService.proxy;

import com.niit.taskService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-authentication-service",url = "localhost:8081")
public interface UserProxy {
    @PostMapping(value = "/api/user/register")
    public Object saveUser(@RequestBody User user) ;

    @PostMapping(value = "api/user/login")
    public Object loginUser(@RequestBody User user) ;
}
