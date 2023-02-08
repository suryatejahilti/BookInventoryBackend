package app.library.usermgmt.user.controller;

import app.library.usermgmt.models.AuthenticationResponse;
import app.library.usermgmt.user.service.UserServiceImpl;
import app.library.usermgmt.models.AuthenticationRequest;
import app.library.usermgmt.models.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegistrationRequest registrationRequest) throws Exception {
            return  ResponseEntity.ok(userServiceImpl.registerUser(registrationRequest));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        return  ResponseEntity.ok(userServiceImpl.loginUser(authenticationRequest));
    }
}
