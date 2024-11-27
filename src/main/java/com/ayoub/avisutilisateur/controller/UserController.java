package com.ayoub.avisutilisateur.controller;

import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("inscription")
    public void inscription(@RequestBody User user) {
        userService.inscription(user);
    }
}
