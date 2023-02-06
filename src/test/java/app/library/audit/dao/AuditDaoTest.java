package app.library.audit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.audit.entity.Audit;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuditDao.class})
@ExtendWith(SpringExtension.class)
class AuditDaoTest {
    @Autowired
    private AuditDao auditDao;

    @MockBean
    private AuditRepository auditRepository;

    @Test
    void testAddAudit() {
        Audit audit = new Audit(123L,"42",mock(Timestamp.class),"User","Action");
        when(auditRepository.save((Audit) any())).thenReturn(audit);
        Audit audit1 = new Audit(123L,"42",mock(Timestamp.class),"User","Action");
        auditDao.addAudit(audit1);
        verify(auditRepository).save((Audit) any());
        assertEquals("Action", audit1.getAction());
        assertEquals("User", audit1.getUser());
        assertEquals("42", audit1.getBookId());
        assertEquals(123L, audit1.getAuditId());
    }
}

