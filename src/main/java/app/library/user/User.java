package app.library.user;

import app.library.models.RegistrationRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.name = registrationRequest.getPassword();
        this.password = registrationRequest.getName();
        this.active = true;
        this.roles = "USER";
    }
}
