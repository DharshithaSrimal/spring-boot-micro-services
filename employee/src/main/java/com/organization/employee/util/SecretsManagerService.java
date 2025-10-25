package com.organization.employee.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Map;

@Service
public class SecretsManagerService {
    private String cachedApiKey;
    private long lastFetchTime = 0;
    private static final long CACHE_DURATION = 3600000; // 1 hour
    private static final String SECRET_NAME = "employee-api/apikey";
    private static final String AWS_REGION = "us-east-1";

    public synchronized String getApiKey() {
        long currentTime = System.currentTimeMillis();

        // Return cached key if still valid
        if (cachedApiKey != null && (currentTime - lastFetchTime) < CACHE_DURATION) {
            System.out.println("Using cached API Key");
            return cachedApiKey;
        }

        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of(AWS_REGION))
                .build()) {

            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId(SECRET_NAME)
                    .build();

            GetSecretValueResponse response = client.getSecretValue(request);
            String secretString = response.secretString();

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> secretMap = mapper.readValue(secretString, Map.class);
            cachedApiKey = (String) secretMap.get("employeeApiKey");
            lastFetchTime = currentTime;

            System.out.println("✓ API Key fetched from AWS Secrets Manager");
            return cachedApiKey;

        } catch (Exception e) {
            System.err.println("✗ Error fetching secret: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve API key from AWS Secrets Manager", e);
        }
    }
}
