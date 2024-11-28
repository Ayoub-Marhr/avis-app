package com.ayoub.avisutilisateur.controller;

import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("inscription")
    public void inscription(@RequestBody User user) {
        userService.inscription(user);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("activation")
    public void validation(@RequestBody Map<String,String> activation) {
        userService.activation(activation);
    }
}
