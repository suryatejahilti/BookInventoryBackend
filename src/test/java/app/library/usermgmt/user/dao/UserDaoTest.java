package app.library.usermgmt.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.usermgmt.user.entity.User;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDao.class})
@ExtendWith(SpringExtension.class)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testAddUser() {
        User user = new User();
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setActive(true);
        user1.setId(123L);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles("Roles");
        user1.setUserName("janedoe");
        userDao.addUser(user1);
        verify(userRepository).save((User) any());
        assertEquals(123L, user1.getId().longValue());
        assertTrue(user1.isActive());
        assertEquals("janedoe", user1.getUserName());
        assertEquals("Roles", user1.getRoles());
        assertEquals("iloveyou", user1.getPassword());
        assertEquals("Name", user1.getName());
    }


    @Test
    void testFindByUserName() {
        User user = new User();
        user.setActive(true);
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles("Roles");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUserName((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByUserNameResult = userDao.findByUserName("janedoe");
        assertSame(ofResult, actualFindByUserNameResult);
        assertTrue(actualFindByUserNameResult.isPresent());
        verify(userRepository).findByUserName((String) any());
    }
}

