package repository;

import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    void insertUser(User user);
    void insertUser(List<User> userList);
    List<User> getAllUser();
    User getUserById(String userId);

}
