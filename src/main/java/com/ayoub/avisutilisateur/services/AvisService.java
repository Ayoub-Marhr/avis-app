package com.ayoub.avisutilisateur.services;

import com.ayoub.avisutilisateur.entities.Avis;
import com.ayoub.avisutilisateur.repositories.AvisRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AvisService {
    @Autowired
    private AvisRepo avisRepo;

    public void createAvis(Avis avis) {
        avisRepo.save(avis);
    }

}
