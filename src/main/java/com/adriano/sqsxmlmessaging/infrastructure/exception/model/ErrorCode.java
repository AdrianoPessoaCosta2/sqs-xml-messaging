package com.adriano.sqsxmlmessaging.infrastructure.exception.model;

public enum ErrorCode {
   XML_CONVERSION_ERROR("ERR001"),
   HANDLER_NOT_FOUND("ERR002"),
   UNEXPECTED_ERROR("ERR003"),
   ERROR_CONVERT_XML("ERR004"),
   CUSTOMER_NOT_FOUND("ERR005");

    private String code;

    ErrorCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
