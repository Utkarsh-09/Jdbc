package service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void insertUser(User user);
    void insertUser(List<User> userList);
    void getAllUser();
    void getUserById(String userId);

}
