package app.library.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService{
    @Autowired
    AuditDao auditDao;

    ObjectMapper mapper = new ObjectMapper();
    @KafkaListener(topics="books", groupId="myGroup")
    public void consume(String message) throws JsonProcessingException {
        auditDao.addAudit(mapper.readValue(message, Audit.class));
    }

}
