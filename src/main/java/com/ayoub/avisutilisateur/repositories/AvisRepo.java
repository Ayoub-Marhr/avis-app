package com.ayoub.avisutilisateur.repositories;

import com.ayoub.avisutilisateur.entities.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepo extends CrudRepository<Avis, Integer> {
}
