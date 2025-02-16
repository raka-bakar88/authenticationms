package com.challenge.authenticationms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponse {
    private String requestId;
    private RequestType type;
    private String payload;
}
