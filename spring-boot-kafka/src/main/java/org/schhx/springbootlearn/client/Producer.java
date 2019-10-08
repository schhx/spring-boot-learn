package org.schhx.springbootlearn.client;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author shanchao
 * @date 2019-04-05
 */
public class Producer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "schema url");

        KafkaProducer producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic", "key", "value");
        try {
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    // do something
                }
            });
        } catch (Exception e) {
            // do something
        }
    }
}
