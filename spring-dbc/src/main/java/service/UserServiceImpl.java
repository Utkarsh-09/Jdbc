package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    @Override
    public void insertUser(List<User> userList) {
        userRepository.insertUser(userList);
    }

    @Override
    public void getAllUser() {
        List<User> userList = userRepository.getAllUser();
        for (User user: userList) {
            System.out.println(user.toString());
        }
    }

    @Override
    public void getUserById(String userId) {
        User user = userRepository.getUserById(userId);
        System.out.println(user);
    }
}
