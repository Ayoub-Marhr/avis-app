package com.ayoub.avisutilisateur.services;

import com.ayoub.avisutilisateur.entities.Role;
import com.ayoub.avisutilisateur.entities.TypeRole;
import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.entities.Validation;
import com.ayoub.avisutilisateur.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ValidationService validationService;

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
       User userCreer =  userRepo.save(user);
       validationService.addValidation(userCreer);
    }

    public void activation(Map<String, String> activation) {
        Validation validation =validationService.getValidation(activation.get("code"));
        if(Instant.now().isAfter(validation.getExpire())){
            throw new RuntimeException("expired code");
        }
        User user =this.userRepo.findById(validation.getUser().getId()).orElseThrow(()-> new RuntimeException("user not found"));
        user.setActive(true);
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return  this.userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));

    }
}
