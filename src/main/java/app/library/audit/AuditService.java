package app.library.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    @Autowired
    AuditRepository auditRepository;

    private KafkaTemplate<String,String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditService.class);


    ObjectMapper mapper = new ObjectMapper();
    @KafkaListener(topics="books", groupId="myGroup")
    public void consume(String message) throws JsonProcessingException {

        LOGGER.info(String.format("message received -> %s", message));
        auditRepository.save(mapper.readValue(message, Audit.class));
    }

}
