package app.library.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    /*
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="books", groupId="myGroup")
    public void consume(String message){
        LOGGER.info(String.format("message received -> %s", message));
    }
    */
}
