package com.challenge.authenticationms.config;

import com.challenge.authenticationms.model.WhiteList;
import com.challenge.authenticationms.repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * a class to populate table whitelist everytime the application is run
 */
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (authenticationRepository.count() == 0) {
            WhiteList record1 = new WhiteList();
            record1.setIdentifier("id1abcdefghijklnmnopqrstuvwxy");
            record1.setStatus(true);

            WhiteList record2 = new WhiteList();
            record2.setIdentifier("id29876543210987654321");
            record2.setStatus(false);

            authenticationRepository.save(record2);
            authenticationRepository.save(record1);
        }
    }
}
