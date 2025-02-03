package com.challenge.authenticationms.utils;

public class AppConstants {
    private AppConstants() {
    }

    //kafka
    public static final String BOOTSTRAP_SERVERS_CONFIG = "localhost:9092";
    public static final String GROUP_ID_CONFIG = "transaction-group-id";
    public static final String AUTH_REQ_TOPIC = "authentication_request_topic";
    public static final String AUTH_RES_TOPIC = "authentication_response_topic";

    public static final int MINIMUM_LENGTH_IDENTIFIER = 20;
    public static final int MAXIMUM_LENGTH_IDENTIFIER = 80;
}
