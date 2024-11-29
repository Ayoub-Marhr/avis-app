package com.ayoub.avisutilisateur.controller;

import com.ayoub.avisutilisateur.dto.AuthentificationDto;
import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
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
    @PostMapping("connexion")
    public Map<String,String> connexion(@RequestBody AuthentificationDto user) {
        final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
        log.info("authenticate {}", authenticate.isAuthenticated());
        return null;
    }
}
