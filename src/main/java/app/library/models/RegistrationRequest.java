package app.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private String username;
    private String name;
    private String password;

}
