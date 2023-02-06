package app.library.usermgmt.models;

import app.library.usermgmt.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
