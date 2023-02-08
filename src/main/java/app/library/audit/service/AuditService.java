package app.library.audit.service;

import app.library.common.exceptions.InvalidAuditRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface AuditService {
    public void consume(String message) throws JsonProcessingException, InvalidAuditRequest;
}
