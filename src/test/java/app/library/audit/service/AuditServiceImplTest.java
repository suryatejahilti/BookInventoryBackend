package app.library.audit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import app.library.audit.dao.AuditDao;
import app.library.common.exceptions.InvalidAuditRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuditServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuditServiceImplTest {
    @MockBean
    private AuditDao auditDao;

    @Autowired
    private AuditServiceImpl auditServiceImpl;


    @Test
    void testConsume() throws InvalidAuditRequest, JsonProcessingException {
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume("Not all who wander are lost"));
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume("Invaid Audit Request"));
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume("42"));
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume(""));
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume("42Not all who wander are lost"));
        assertThrows(InvalidAuditRequest.class, () -> auditServiceImpl.consume("4242"));
    }
}

