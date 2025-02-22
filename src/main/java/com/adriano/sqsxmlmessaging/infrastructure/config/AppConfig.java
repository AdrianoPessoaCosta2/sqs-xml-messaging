package com.adriano.sqsxmlmessaging.infrastructure.config;

import com.adriano.sqsxmlmessaging.infrastructure.exception.ErrorMessageService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.ErrorMessagesLoadException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ErrorMessageService errorMessageService() {
        try {
            return new ErrorMessageService("error-codes.properties");
        } catch (ErrorMessagesLoadException ex) {
            throw new RuntimeException("Erro ao inicializar o ErrorMessageService", ex);
        }
    }
}
