package app.library.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import javax.persistence.*;
import java.time.Instant;
@Entity
@Getter
@Setter
@Table(name="audit")
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long auditId;
    @Column(name="bookid")
    String bookId;

    @Column(name="timestamp")
    Timestamp updatedOn=Timestamp.from(Instant.now());

    @Column(name="userName")
    String user;
    @Column(name="action")
    String action;

    public Audit(String bookid,String user, String action) {
        this.bookId = bookid;
        this.user=user;
        this.action = action;
    }


}
