package app.library.audit.service;

import app.library.audit.dao.AuditDao;
import app.library.audit.entity.Audit;
import app.library.common.JsonParser;
import app.library.common.exceptions.InvalidAuditRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static app.library.common.BookConstants.TOPIC_NAME;

@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    AuditDao auditDao;

    @KafkaListener(topics=TOPIC_NAME, groupId="myGroup")
    public void consume(String message) throws JsonProcessingException, InvalidAuditRequest {
        try {
            auditDao.addAudit(JsonParser.parser(message));
        }
        catch(Exception ex){
            throw new InvalidAuditRequest("Invaid Audit Request");
        }
    }

}
