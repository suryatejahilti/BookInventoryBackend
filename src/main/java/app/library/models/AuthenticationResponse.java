package app.library.models;

import app.library.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String accessToken;
    private String roles;
    private String name;




    public AuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.roles = user.getRoles();
        this.name = user.getName();
    }

}
