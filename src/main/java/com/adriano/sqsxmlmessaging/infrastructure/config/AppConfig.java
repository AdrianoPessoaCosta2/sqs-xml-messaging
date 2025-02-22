package com.adriano.sqsxmlmessaging.infrastructure.config;

import com.adriano.sqsxmlmessaging.infrastructure.exception.ErrorMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ErrorMessageService errorMessageService(){
        return new ErrorMessageService("error-codes.properties");
    }
}
