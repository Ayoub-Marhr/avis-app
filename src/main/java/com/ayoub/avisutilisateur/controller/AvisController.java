package com.ayoub.avisutilisateur.controller;

import com.ayoub.avisutilisateur.entities.Avis;
import com.ayoub.avisutilisateur.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avis")
public class AvisController {
    @Autowired
    private AvisService avisService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addAvis")
    public void addAvis(@RequestBody Avis avis) {
        avisService.createAvis(avis);
    }
}
