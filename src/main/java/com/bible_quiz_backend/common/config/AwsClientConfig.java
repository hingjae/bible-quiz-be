package com.bible_quiz_backend.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsClientConfig {
    @Bean
    public SqsAsyncClient sqsAsyncClient(
            @Value("${aws.queue-region}") String region,
            @Value("${aws.access-key}") String awsAccessKey,
            @Value("${aws.secret-key}") String awsSecretKey
    ) {
        return SqsAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKey, awsSecretKey))
                )
                .build();
    }
}
