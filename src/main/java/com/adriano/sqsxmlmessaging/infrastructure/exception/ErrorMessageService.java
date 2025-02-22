package com.adriano.sqsxmlmessaging.infrastructure.exception;

import com.adriano.sqsxmlmessaging.infrastructure.exception.model.ErrorCode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorMessageService {
    private Properties errorMessages;

    public ErrorMessageService(String errorFile) throws ErrorMessagesLoadException {
        loadErrorMessages(errorFile);
    }

    private void loadErrorMessages(String errorFile) throws ErrorMessagesLoadException{
        errorMessages = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(errorFile)) {
            if (input == null) {
                throw new ErrorMessagesLoadException("Desculpe, não foi possível encontrar o arquivo de configuração de erros.");
            }
            errorMessages.load(input);
        } catch (IOException ex) {
            throw new ErrorMessagesLoadException("Erro ao carregar o arquivo de configuração de erros.", ex);
        }
    }

    public String getErrorMessage(ErrorCode errorCode) {
        return errorMessages.getProperty(errorCode.name(), "Mensagem de erro desconhecida.");
    }
}
