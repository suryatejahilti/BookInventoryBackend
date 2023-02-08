package app.library.usermgmt.authmgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.usermgmt.user.dao.UserDao;
import app.library.usermgmt.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MyUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class MyUserDetailsServiceTest {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private UserDao userDao;


    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = myUserDetailsService.loadUserByUsername("janedoe");
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("Roles", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
        verify(userDao).findByUserName((String) any());
    }


    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = mock(User.class);
        when(user.isActive()).thenReturn(false);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn("Roles");
        when(user.getUserName()).thenReturn("janedoe");
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
        UserDetails actualLoadUserByUsernameResult = myUserDetailsService.loadUserByUsername("janedoe");
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertFalse(actualLoadUserByUsernameResult.isEnabled());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("Roles", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
        verify(userDao).findByUserName((String) any());
        verify(user).isActive();
        verify(user).getPassword();
        verify(user).getRoles();
        verify(user).getUserName();
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
    }





    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn("Roles");
        when(user.getUserName()).thenReturn("janedoe");
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
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(userDao).findByUserName((String) any());
        verify(user).setActive(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((String) any());
        verify(user).setUserName((String) any());
    }
}

