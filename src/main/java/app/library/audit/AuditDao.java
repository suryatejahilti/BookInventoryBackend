package app.library.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDao {
    @Autowired
    AuditRepository auditRepository;

    public void addAudit(Audit audit) {
        auditRepository.save(audit);
    }
}
