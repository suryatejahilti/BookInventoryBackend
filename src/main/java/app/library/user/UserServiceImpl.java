package app.library.user;

import app.library.models.AuthenticationRequest;
import app.library.models.AuthenticationResponse;
import app.library.models.RegistrationRequest;
import app.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDao userDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    public ResponseEntity<?> registerUser(RegistrationRequest registrationRequest) throws Exception {
        userDao.addUser(new User(registrationRequest));
        return loginUser(new AuthenticationRequest(registrationRequest));
    }


    public ResponseEntity<?> loginUser(AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        } catch(Exception e){
            throw new Exception("exception in login",e);
        }

        final Optional<User> user =userDao.findByUserName(authenticationRequest.getUsername());
         String Jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername()));


        return ResponseEntity.ok(new AuthenticationResponse(Jwt, user.get()));
    }

}
