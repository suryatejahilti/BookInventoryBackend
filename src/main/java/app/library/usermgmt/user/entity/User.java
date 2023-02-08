package app.library.usermgmt.user.entity;

import app.library.common.UserRole;
import app.library.usermgmt.models.RegistrationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
//change the string user to the constant
@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private String userName;

    private String name;
    private String password;
    private boolean active;
    private String roles;

    public String getName() {
        return name;
    }


    public User(RegistrationRequest registrationRequest) {
        this.userName = registrationRequest.getUsername();
        this.name = registrationRequest.getName();
        this.password = new BCryptPasswordEncoder().encode(registrationRequest.getPassword());
        this.active = true;
        this.roles =  UserRole.USER.getValue();

    }
}
