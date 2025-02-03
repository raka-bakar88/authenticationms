package com.challenge.authenticationms.service;


import com.challenge.authenticationms.model.MessageRequest;
import com.challenge.authenticationms.model.MessageResponse;

public interface AuthenticationService {
    MessageResponse authenticate(MessageRequest request);
}
