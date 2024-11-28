package com.ayoub.avisutilisateur.services;

import com.ayoub.avisutilisateur.entities.User;
import com.ayoub.avisutilisateur.entities.Validation;
import com.ayoub.avisutilisateur.repositories.ValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class ValidationService {
    @Autowired
    private ValidationRepo validationRepo;
    @Autowired
    private NotificationService notificationService;

    public void addValidation(User User) {
        Validation validation = new Validation();
        validation.setUser(User);

        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expriration = creation.plus(10, MINUTES);
        validation.setExpire(expriration);
        Random random = new Random();
       int randomInt= random.nextInt(999999);
        String code = String.format("%06d",randomInt);
        validation.setCode(code);
        validationRepo.save(validation);
        notificationService.envoyer(validation);

    }
    public Validation getValidation(String code) {
        return validationRepo.findByCode(code).orElseThrow(()->new RuntimeException("Validation not found"));
    }
}
