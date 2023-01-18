package app.library.models;

import app.library.user.User;

import java.util.List;

public class AuthenticationResponse {
    private final String accessToken;
    private String roles;
    private String name;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public AuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.roles = user.getRoles();
        this.name = user.getName();
    }

}
