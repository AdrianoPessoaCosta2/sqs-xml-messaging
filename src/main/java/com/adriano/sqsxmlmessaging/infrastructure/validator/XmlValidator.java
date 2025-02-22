package com.adriano.sqsxmlmessaging.infrastructure.validator;

import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class XmlValidator {

    private static final Logger logger = LoggerFactory.getLogger(XmlValidator.class);

    public static boolean validateXML(String xmlString, MessageDatatype messageDatatype) {
        try {
            logger.debug("Inicialdo validação xsd para o datatype: " + messageDatatype.name());
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            String path = "xsd/" + messageDatatype.getDtoClass().concat(".xsd");

            ClassPathResource xsdResource = new ClassPathResource(path);

            Schema schema = factory.newSchema(new StreamSource(xsdResource.getInputStream()));

            Validator validator = schema.newValidator();

            StringReader stringReader = new StringReader(xmlString);

            validator.validate((new StreamSource(stringReader)));

            return true;
        } catch (SAXException e) {
            logger.error("Erro de validação: " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("Erro de I/O: " + e.getMessage());
            return false;
        }
    }
}
