package com.ayoub.avisutilisateur.services;

import com.ayoub.avisutilisateur.entities.Role;
import com.ayoub.avisutilisateur.entities.TypeRole;
import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void inscription(User user) {
        if(!user.getEmail().contains("@")) {
            throw new RuntimeException("the email is unvalide");
        }

        Optional<User> optionalUser = this.userRepo.findByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            throw new RuntimeException("the email is already in use");
        }
        String password =bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        Role role = new Role();
        role.setRole(TypeRole.USER);
        user.setRole(role);
        userRepo.save(user);
    }
}
