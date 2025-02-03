package com.challenge.authenticationms.model;

import lombok.Getter;

@Getter
public enum AuthenticationStatus {
    ACCEPTED("accepted"),

    UNKNOWN("unknown"),

    INVALID("invalid"),

    REJECTED("rejected");

    private final String status;

    AuthenticationStatus(String status) {
        this.status = status;
    }
}
