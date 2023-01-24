package app.library.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import java.sql.Timestamp;
import javax.persistence.*;
import java.time.Instant;
@Entity
@Getter
@Setter
@Table(name="audit")
@NoArgsConstructor
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long auditId;
    @Column(name="bookid")
    long bookid;

    @Column(name="timestamp")
    Timestamp updatedOn=Timestamp.from(Instant.now());;
    @Column(name="action")
    String action;

    public Audit(long bookid, String action) {
        this.bookid = bookid;
        this.action = action;
    }
}
