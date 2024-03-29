package app.library.usermgmt.user.dao;

import app.library.usermgmt.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user) { userRepository.save(user);}
    public Optional<User> findByUserName(String userName) {return userRepository.findByUserName(userName);}
}
