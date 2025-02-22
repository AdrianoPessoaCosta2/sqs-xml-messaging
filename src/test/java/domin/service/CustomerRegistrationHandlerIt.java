package domin.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
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

    @BeforeEach
    void initTest() throws Exception {
        DatabaseCleanup.clearDatabase();
    }

    @AfterEach
    void afterTest() throws Exception {
//        DataSetAfterExtension.loadAndValidateYamlDataSet("dataset/success.yml");

    }

    @Test
    @DataSetAfter
    void success() {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create("https://sqs.us-east-1.localhost.localstack.cloud:4566")) // URL correta do LocalStack
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .build();
        String queueUrl = "https://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/main-queue";

        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(loadResourceFile("CustomerRegistration.xml"))
                .build();

        sqsClient.sendMessage(sendMsgRequest);
    }

    private static String loadResourceFile(String fileName) {
        try (InputStream inputStream = CustomerRegistrationHandlerIt.class.getClassLoader().getResourceAsStream("xml/" + fileName)) {
            if (inputStream == null) {
                return null;
            }
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
