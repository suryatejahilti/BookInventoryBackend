package app.library.usermgmt.authmgmt;

import app.library.usermgmt.user.entity.User;
import app.library.usermgmt.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user= userDao.findByUserName(username);
            if (user.isPresent()){
                return user.map(MyUserDetails::new).get();
            }
            else {
                throw new UsernameNotFoundException("Not found : "+ username);
            }


    }
}
