package com.challenge.authenticationms.messaging;


import com.challenge.authenticationms.model.MessageRequest;
import com.challenge.authenticationms.model.MessageResponse;
import com.challenge.authenticationms.service.AuthenticationService;
import com.challenge.authenticationms.utils.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    @KafkaListener(topics = AppConstants.AUTH_REQ_TOPIC, groupId = AppConstants.GROUP_ID_CONFIG)
    public void listen(String messageJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MessageRequest authenticationRequest = objectMapper.readValue(messageJson, MessageRequest.class);
            logger.info("Message consumed topic: {}", AppConstants.AUTH_REQ_TOPIC);
            logger.info("Message consumed message: {}", messageJson);
            MessageResponse response = authenticationService.authenticate(authenticationRequest);

            String responseMessageJson = new ObjectMapper().writeValueAsString(response);
            kafkaTemplate.send(AppConstants.AUTH_RES_TOPIC, responseMessageJson);
            logger.info("Send Kafka topic : " + AppConstants.AUTH_RES_TOPIC);
            logger.info("Send Kafka message : {}", response);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
