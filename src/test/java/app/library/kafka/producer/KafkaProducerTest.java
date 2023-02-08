package app.library.kafka.producer;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.common.exceptions.KafkaProducerException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaProducer.class})
@ExtendWith(SpringExtension.class)
class KafkaProducerTest {
    @Autowired
    private KafkaProducer kafkaProducer;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    void testSendMessage() throws KafkaProducerException {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("Topic", "42");

        when(kafkaTemplate.send((String) any(), (String) any())).thenReturn(new AsyncResult<>(
                new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));
        kafkaProducer.sendMessage("Not all who wander are lost");
        verify(kafkaTemplate).send((String) any(), (String) any());
    }
}

