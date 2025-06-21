package com.krowfeather.controller;

import com.krowfeather.entity.User;
import com.krowfeather.entity.Result;
import com.krowfeather.entity.Const;
import com.krowfeather.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/consumer/users")
public class UserController {
    // private static final String PROVIDER_SERVICE = "http://provider-service/api/users";

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable(name = "id") Integer id) {
        User user = userFeignClient.getUserById(id);
        return new Result<>(Const.CODE_SUCCESS, Const.MSG_SUCCESS, user);
    }

    @PostMapping
    public Result<User> createUser(@RequestBody User user) {
        User created = userFeignClient.createUser(user);
        return new Result<>(Const.CODE_SUCCESS, Const.MSG_SUCCESS, created);
    }

    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        User updated = userFeignClient.updateUser(id, user);
        return new Result<>(Const.CODE_SUCCESS, Const.MSG_SUCCESS, updated);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUser(@PathVariable(name = "id") Integer id) {
        userFeignClient.deleteUser(id);
        return new Result<>(Const.CODE_SUCCESS, Const.MSG_SUCCESS, null);
    }
} 