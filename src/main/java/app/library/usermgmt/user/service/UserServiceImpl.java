package app.library.usermgmt.user.service;

import app.library.common.exceptions.InvalidUserDetailsException;
import app.library.common.exceptions.UserExistException;
import app.library.usermgmt.models.AuthenticationRequest;
import app.library.usermgmt.models.AuthenticationResponse;
import app.library.usermgmt.models.RegistrationRequest;
import app.library.usermgmt.user.dao.UserDao;
import app.library.usermgmt.user.entity.User;
import app.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public AuthenticationResponse registerUser(RegistrationRequest registrationRequest) throws Exception {
        try {
            userDao.addUser(new User(registrationRequest));
        }
        catch (Exception err){
            throw new UserExistException("user already exsists");
        }
        return loginUser(new AuthenticationRequest(registrationRequest));
    }


    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e){
            throw new InvalidUserDetailsException("Incorrect username or password");
        }

        final Optional<User> user =userDao.findByUserName(authenticationRequest.getUsername());
         String jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername()));
         if (user.isPresent()) {
             return new AuthenticationResponse(jwt, user.get());
         }
         else {
              throw new InvalidUserDetailsException("invaild username or passowrd");
         }
    }

}
