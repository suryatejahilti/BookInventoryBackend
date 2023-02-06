package app.library.usermgmt.user.service;

import app.library.usermgmt.models.AuthenticationRequest;
import app.library.usermgmt.models.AuthenticationResponse;
import app.library.usermgmt.models.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public AuthenticationResponse registerUser(RegistrationRequest registrationRequest) throws Exception;
    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest)throws Exception;
}
