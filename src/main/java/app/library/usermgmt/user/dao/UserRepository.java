package app.library.usermgmt.user.dao;

import app.library.usermgmt.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

     Optional<User> findByUserName(String userName);

}
