package app.library.util;

import app.library.usermgmt.authmgmt.MyUserDetails;
import app.library.usermgmt.models.RegistrationRequest;
import app.library.usermgmt.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JwtUtil.class})
@ExtendWith(SpringExtension.class)
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;


    @Test
    void testExtractUsername() {
        assertEquals("hello@gmail.com",jwtUtil.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJleHAiOjE5NzUxODgyNTgsImlhdCI6MTY3NTE4NDY1Mn0.Avdd30TOXAlHaTQVqnAFu18XZ6bGE3B_mp33lHJkJow"));
    }


    @Test
    void testExtractExpiration() {

        assertTrue(jwtUtil.extractExpiration("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJleHAiOjE5NzUxODgyNTgsImlhdCI6MTY3NTE4NDY1Mn0.Avdd30TOXAlHaTQVqnAFu18XZ6bGE3B_mp33lHJkJow").compareTo(Date.from(Instant.now()))>0) ;
    }




    @Test
    void testGenerateToken() {
        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn("Roles");
        when(user.getUserName()).thenReturn("janedoe");
        jwtUtil.generateToken(new MyUserDetails(user));
        verify(user).isActive();
        verify(user).getPassword();
        verify(user).getRoles();
        verify(user).getUserName();
    }


    @Test
    void testValidateToken() {

        jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJleHAiOjE5NzUxODgyNTgsImlhdCI6MTY3NTE4NDY1Mn0.Avdd30TOXAlHaTQVqnAFu18XZ6bGE3B_mp33lHJkJow",
                new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
    }

    @Test
    void testValidateToken2() {
        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn("Roles");
        when(user.getUserName()).thenReturn("janedoe");
        jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJleHAiOjE5NzUxODgyNTgsImlhdCI6MTY3NTE4NDY1Mn0.Avdd30TOXAlHaTQVqnAFu18XZ6bGE3B_mp33lHJkJow", new MyUserDetails(user));
    }
}

