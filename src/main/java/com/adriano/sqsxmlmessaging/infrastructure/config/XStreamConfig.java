package com.adriano.sqsxmlmessaging.infrastructure.config;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.application.dto.CustomerRegistration;
import com.adriano.sqsxmlmessaging.application.dto.Invoice;
import com.adriano.sqsxmlmessaging.application.dto.Invoices;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XStreamConfig {

    @Bean
    public XStream xstream(){
        XStream xStream = new XStream(new DomDriver());

        xStream.alias("CustomerRegistration", CustomerRegistration.class);
        xStream.alias("Customer", Customer.class);
        xStream.alias("Invoices", Invoices.class);
        xStream.alias("Invoice", Invoice.class);

        xStream.processAnnotations(CustomerRegistration.class);
        xStream.processAnnotations(Customer.class);
        xStream.processAnnotations(Invoices.class);
        xStream.processAnnotations(Invoice.class);

        XStream.setupDefaultSecurity(xStream);

        xStream.allowTypes(new Class[]{CustomerRegistration.class, Invoices.class, Invoice.class, Customer.class});

        return xStream;
    }
}
