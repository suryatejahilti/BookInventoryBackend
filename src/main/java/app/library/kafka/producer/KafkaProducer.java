package app.library.kafka.producer;

import app.library.common.exceptions.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static app.library.common.BookConstants.TOPIC_NAME;

@Service
public class KafkaProducer {

    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message) throws KafkaProducerException {
        try {
            kafkaTemplate.send(TOPIC_NAME, message);
        }
        catch (Exception ex){
            throw new KafkaProducerException("kafka could not send the message");
        }
    }


}
