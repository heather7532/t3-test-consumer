package org.alpenlogic.tools.examples.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

	private final LocalKafkaProperties kprops;

	public KafkaConsumerConfig(LocalKafkaProperties localKafkaProperties) {
		this.kprops = localKafkaProperties;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kprops.getConsumer().getBootstrapServers());
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kprops.getConsumer().getKeyDeserializer());
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kprops.getConsumer().getValueDeserializer());
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kprops.getConsumer().getGroupId());
		configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kprops.getConsumer().getEnableAutoCommit());
		configProps.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kprops.getConsumer().getAutoCommitInterval());
		configProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kprops.getConsumer().getSessionTimeout());
		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kprops.getConsumer().getAutoOffsetReset());
		configProps.put("schema.registry.url", kprops.getSchemaRegistryUrl());

		DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(configProps);

		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
}