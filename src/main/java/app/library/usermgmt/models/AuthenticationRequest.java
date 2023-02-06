package app.library.usermgmt.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest(RegistrationRequest registrationRequest) {
        this.username= registrationRequest.getUsername();
        this.password=registrationRequest.getPassword();
    }


}
