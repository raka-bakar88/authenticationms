package com.challenge.authenticationms;

import com.challenge.authenticationms.model.*;
import com.challenge.authenticationms.repository.AuthenticationRepository;
import com.challenge.authenticationms.service.AuthenticationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    @Mock
    private AuthenticationRepository authenticationRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAcceptedIdentifier() {
        MessageRequest request = new MessageRequest();
        request.setPayload("id1abcdefghijklnmnopqrstuvwxy");
        request.setRequestId("newRequestId");
        request.setType(RequestType.AUTHENTICATION_REQUEST);

        WhiteList record = new WhiteList();
        record.setIdentifier("id1abcdefghijklnmnopqrstuvwxy");
        record.setStatus(true);

        when(authenticationRepository.findByIdentifier("id1abcdefghijklnmnopqrstuvwxy")).thenReturn(Optional.of(record));

        MessageResponse response = authenticationService.authenticate(request);
        assertEquals(AuthenticationStatus.ACCEPTED.name(), response.getPayload());
    }

    @Test
    void testRejectedIdentifier() {
        MessageRequest request = new MessageRequest();
        request.setPayload("id1abcdefghijklnmnopqrstuvwxy");
        request.setRequestId("newRequestId");
        request.setType(RequestType.AUTHENTICATION_REQUEST);

        WhiteList record = new WhiteList();
        record.setIdentifier("id1abcdefghijklnmnopqrstuvwxy");
        record.setStatus(false);

        when(authenticationRepository.findByIdentifier("id1abcdefghijklnmnopqrstuvwxy")).thenReturn(Optional.of(record));

        MessageResponse response = authenticationService.authenticate(request);
        assertEquals(AuthenticationStatus.REJECTED.name(), response.getPayload());
    }

    @Test
    void testUnknownIdentifier() {
        MessageRequest request = new MessageRequest();
        request.setPayload("unknownId123456788910werty");
        request.setRequestId("newRequestId");
        request.setType(RequestType.AUTHENTICATION_REQUEST);

        when(authenticationRepository.findByIdentifier("unknownId123456788910werty")).thenReturn(Optional.empty());

        MessageResponse response = authenticationService.authenticate(request);
        assertEquals(AuthenticationStatus.UNKNOWN.name(), response.getPayload());
    }

    @Test
    void testInvalidIdentifier() {
        MessageRequest request = new MessageRequest();
        request.setPayload("id");
        request.setRequestId("newRequestId");
        request.setType(RequestType.AUTHENTICATION_REQUEST);

        MessageResponse response = authenticationService.authenticate(request);
        assertEquals(AuthenticationStatus.INVALID.name(), response.getPayload());
    }
}
