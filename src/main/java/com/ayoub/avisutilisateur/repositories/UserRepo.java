package com.ayoub.avisutilisateur.repositories;

import com.ayoub.avisutilisateur.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
