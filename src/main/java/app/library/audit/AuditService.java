package app.library.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface AuditService {
    public void consume(String message) throws JsonProcessingException;
}
