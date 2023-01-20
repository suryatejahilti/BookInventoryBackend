package app.library.user;

import app.library.book.Book;
import app.library.models.AuthenticationRequest;
import app.library.models.AuthenticationResponse;
import app.library.models.RegistrationRequest;
import app.library.usermgmt.MyUserDetails;
import app.library.util.JwtUtil;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public void createUser(RegistrationRequest registrationRequest){
        User user = new User();
        user.setUserName(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setName(registrationRequest.getName());
        user.setRoles("USER");
        user.setActive(true);
        userRepository.save(user);
    }
    public ResponseEntity<?> loginUser(AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final Optional<User> user =userRepository.findByUserName(authenticationRequest.getUsername());


         String Jwt = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok(new AuthenticationResponse(Jwt,user.get()));
    }

}
