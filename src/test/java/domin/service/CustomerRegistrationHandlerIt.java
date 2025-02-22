package domin.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.PurgeQueueRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import util.DataSetAfter;
import util.DataSetAfterExtension;
import util.DatabaseCleanup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@ExtendWith(DataSetAfterExtension.class)
class CustomerRegistrationHandlerIt {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationHandlerIt.class);
    private static final String queueUrl = "https://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/main-queue";
    private static final URI uri = URI.create("https://sqs.us-east-1.localhost.localstack.cloud:4566");
    @Autowired
    SqsClient sqsClient;

    @BeforeEach
    void initTest() {
        DatabaseCleanup.clearDatabase();
    }

    @AfterEach
    void endTest() {
        sqsClient.purgeQueue(PurgeQueueRequest.builder().queueUrl(queueUrl).build());
    }

    @Test
    @DataSetAfter
    void success() {
        sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(uri)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .build();


        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(loadResourceFile("CustomerRegistration.xml"))
                .build();

        sqsClient.sendMessage(sendMsgRequest);
    }

    private static String loadResourceFile(String fileName) {
        try (InputStream inputStream = CustomerRegistrationHandlerIt.class.getClassLoader().getResourceAsStream("xml/" + fileName)) {
            if (inputStream == null) {
                logger.error("Arquivo não encontrado: {}", fileName);
                return "Arquivo não encontrado: " + fileName;
            }
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            logger.error("Erro ao carregar o arquivo: {}", fileName, e);
            return "Erro ao carregar o arquivo: " + fileName;
        }
    }
}
