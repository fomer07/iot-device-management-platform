package com.example.iot.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Configuration
public class KafkaSslConfig {

    @PostConstruct
    public void setupKafkaSslKeys() throws Exception {
        // Get the base64-encoded environment variables
        String trustStoreBase64 = System.getenv("KAFKA_TRUSTSTORE_BASE64");
        String keyStoreBase64 = System.getenv("KAFKA_KEYSTORE_BASE64");

        // Decode and write the truststore file
        if (trustStoreBase64 != null) {
            byte[] decodedTrustStore = Base64.getDecoder().decode(trustStoreBase64);
            Files.write(Paths.get("/tmp/client.truststore.jks"), decodedTrustStore);
            System.setProperty("javax.net.ssl.trustStore", "/tmp/client.truststore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", System.getenv("KAFKA_TRUSTSTORE_PASSWORD"));
        }

        // Decode and write the keystore file
        if (keyStoreBase64 != null) {
            byte[] decodedKeyStore = Base64.getDecoder().decode(keyStoreBase64);
            Files.write(Paths.get("/tmp/client.keystore.p12"), decodedKeyStore);
            System.setProperty("javax.net.ssl.keyStore", "/tmp/client.keystore.p12");
            System.setProperty("javax.net.ssl.keyStorePassword", System.getenv("KAFKA_KEYSTORE_PASSWORD"));
        }

        System.out.println("Kafka SSL key files have been set up.");
    }

    @PreDestroy
    public void cleanupSslFiles() throws Exception {
        Files.deleteIfExists(Paths.get("/tmp/truststore.jks"));
        Files.deleteIfExists(Paths.get("/tmp/keystore.p12"));
        System.out.println("Kafka SSL key files have been cleaned up.");
    }
}
