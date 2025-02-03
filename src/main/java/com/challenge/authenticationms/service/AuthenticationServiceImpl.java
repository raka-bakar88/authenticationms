package com.challenge.authenticationms.service;

import com.challenge.authenticationms.model.*;
import com.challenge.authenticationms.repository.AuthenticationRepository;
import com.challenge.authenticationms.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public MessageResponse authenticate(MessageRequest request) {
        String identifier = request.getPayload();
        logger.info("authenticate identifier: {}", identifier);
        if (identifier.length() < AppConstants.MINIMUM_LENGTH_IDENTIFIER || identifier.length() > AppConstants.MAXIMUM_LENGTH_IDENTIFIER) {
            return new MessageResponse(request.getRequestId(), RequestType.AUTHENTICATION_RESPONSE, AuthenticationStatus.INVALID.name());
        }

        Optional<WhiteList> result = authenticationRepository.findByIdentifier(identifier);
        if (result.isEmpty()) {
            return new MessageResponse(request.getRequestId(), RequestType.AUTHENTICATION_RESPONSE, AuthenticationStatus.UNKNOWN.name());
        }
        return new MessageResponse(request.getRequestId(), RequestType.AUTHENTICATION_RESPONSE, result.get().isStatus() ? AuthenticationStatus.ACCEPTED.name() : AuthenticationStatus.REJECTED.name());
    }
}
