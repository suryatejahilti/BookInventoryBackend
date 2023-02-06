package app.library.usermgmt.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.common.UserRole;
import app.library.common.exceptions.InvalidUserDetailsException;
import app.library.common.exceptions.UserExistException;
import app.library.usermgmt.authmgmt.MyUserDetails;
import app.library.usermgmt.models.AuthenticationRequest;
import app.library.usermgmt.models.AuthenticationResponse;
import app.library.usermgmt.models.RegistrationRequest;
import app.library.usermgmt.user.dao.UserDao;
import app.library.usermgmt.user.entity.User;
import app.library.util.JwtUtil;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDao userDao;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void testRegisterUser() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        doNothing().when(userDao).addUser((User) any());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        AuthenticationResponse actualRegisterUserResult = userServiceImpl
                .registerUser(new RegistrationRequest("janedoe", "Name", "iloveyou"));
        assertEquals("ABC123", actualRegisterUserResult.getAccessToken());
        assertEquals("Roles", actualRegisterUserResult.getRoles());
        assertEquals("Name", actualRegisterUserResult.getName());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userDao).findByUserName((String) any());
        verify(userDao).addUser((User) any());
        verify(user).getName();
        verify(user).getRoles();
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }

    @Test
    void testRegisterUser2() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        doNothing().when(userDao).addUser((User) any());
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        assertThrows(InvalidUserDetailsException.class,
                () -> userServiceImpl.registerUser(new RegistrationRequest("janedoe", "Name", "iloveyou")));
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userDao).findByUserName((String) any());
        verify(userDao).addUser((User) any());
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }


    @Test
    void testRegisterUser3() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        doNothing().when(userDao).addUser((User) any());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        assertThrows(UserExistException.class, () -> userServiceImpl.registerUser(null));
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
    }


    @Test
    void testLoginUser() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        AuthenticationResponse actualLoginUserResult = userServiceImpl
                .loginUser(new AuthenticationRequest("janedoe", "iloveyou"));
        assertEquals("ABC123", actualLoginUserResult.getAccessToken());
        assertEquals("Roles", actualLoginUserResult.getRoles());
        assertEquals("Name", actualLoginUserResult.getName());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userDao).findByUserName((String) any());
        verify(user).getName();
        verify(user).getRoles();
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }


    @Test
    void testLoginUser2() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        assertThrows(InvalidUserDetailsException.class,
                () -> userServiceImpl.loginUser(new AuthenticationRequest("janedoe", "iloveyou")));
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userDao).findByUserName((String) any());
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }


    @Test
    void testLoginUser3() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(UserRole.ADMIN.getValue());
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        assertThrows(InvalidUserDetailsException.class, () -> userServiceImpl.loginUser(null));
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
    }


    @Test
    void testLoginUser4() throws Exception {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        when(user.getRoles()).thenReturn("Roles");
        doNothing().when(user).setActive(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((String) any());
        doNothing().when(user).setUserName((String) any());
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(UserRole.USER.getValue());
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new MyUserDetails(new User(new RegistrationRequest("janedoe", "Name", "iloveyou"))));
        AuthenticationRequest authenticationRequest = mock(AuthenticationRequest.class);
        when(authenticationRequest.getPassword()).thenReturn("iloveyou");
        when(authenticationRequest.getUsername()).thenReturn("janedoe");
        AuthenticationResponse actualLoginUserResult = userServiceImpl.loginUser(authenticationRequest);
        assertEquals("ABC123", actualLoginUserResult.getAccessToken());
        assertEquals("Roles", actualLoginUserResult.getRoles());
        assertEquals("Name", actualLoginUserResult.getName());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userDao).findByUserName((String) any());
        verify(user).getName();
        verify(user).getRoles();
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
        verify(userDetailsService).loadUserByUsername((String) any());
        verify(authenticationRequest).getPassword();
        verify(authenticationRequest, atLeast(1)).getUsername();
    }
}

