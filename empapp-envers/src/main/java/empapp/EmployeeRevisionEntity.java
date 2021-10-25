package empapp;

import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@RevisionEntity(StubUsernameListener.class)
@Data
public class EmployeeRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    @Column(name = "revision_date")
    private Date revisionDate;

    private String username;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "postman_token")
    private String postmanToken;

}
