package com.ayoub.avisutilisateur.repositories;

import com.ayoub.avisutilisateur.entities.Validation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepo extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}
