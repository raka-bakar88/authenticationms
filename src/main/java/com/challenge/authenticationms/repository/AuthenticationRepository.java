package com.challenge.authenticationms.repository;

import com.challenge.authenticationms.model.WhiteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<WhiteList, Long> {
    Optional<WhiteList> findByIdentifier(String identifier);
}
