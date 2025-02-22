package com.adriano.sqsxmlmessaging.infrastructure.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqsSender {

    @Autowired
    private SqsTemplate sqsTemplate;

    public void sendMessage(){
        sqsTemplate.send("https://localhost.localstack.cloud:4566/000000000000/test-queue", "Hello");
    }
}
