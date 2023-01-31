package app.library.user;

import app.library.models.AuthenticationRequest;
import app.library.models.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseEntity<?> registerUser(RegistrationRequest registrationRequest) throws Exception;
    public ResponseEntity<?> loginUser(AuthenticationRequest authenticationRequest)throws Exception;
}
