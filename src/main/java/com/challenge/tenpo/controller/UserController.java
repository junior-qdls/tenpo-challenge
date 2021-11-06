package com.challenge.tenpo.controller;

import com.challenge.tenpo.model.UserModel;
import com.challenge.tenpo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserModel userModel) {
        userService.singUp(userModel);
        Map<String,Object> response = new HashMap<>();
        response.put("message" , "User has been created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
