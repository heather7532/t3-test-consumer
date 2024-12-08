package org.alpenlogic.tools.examples.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
@Getter
@Setter
public class LocalKafkaProperties {
    private String topic;
    private String schemaRegistryUrl;
    private Consumer consumer;

    @Getter
    @Setter
    public static class Consumer {
        private String keyDeserializer;
        private String valueDeserializer;
        private String bootstrapServers;
        private String groupId;
        private Boolean enableAutoCommit;
        private int autoCommitInterval;
        private int sessionTimeout;
        private String autoOffsetReset;
    }
}