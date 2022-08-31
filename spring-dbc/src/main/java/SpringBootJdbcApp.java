import model.User;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import service.UserService;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(value = {"repository","service"})
public class SpringBootJdbcApp {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        ApplicationContext ac = SpringApplication.run(SpringBootJdbcApp.class, args);
        UserService userService = ac.getBean(UserService.class);

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Mukul");
        user1.setLastName("Sharma");
        user1.setEmail("!23@gmail.com");
        user1.setPassword("123wdasd");
        user1.setMobileNumber("123123123123");
        user1.setAddress1("dasdasd");
        user1.setAddress2("adssdasdad");

        User user2 = new User();
        user1.setId(1);
        user1.setFirstName("abc");
        user1.setLastName("Sharma");
        user1.setEmail("!23@fsfgmail.com");
        user1.setPassword("123wasffasdasd");
        user1.setMobileNumber("13523123");
        user1.setAddress1("dasw522rdasd");
        user1.setAddress2("ads2tfdfdad");

        User user3 = new User();
        user1.setId(1);
        user1.setFirstName("asl");
        user1.setLastName("sarma");
        user1.setEmail("!ffff@gmail.com");
        user1.setPassword("vvvvasd");
        user1.setMobileNumber("vvv123123");
        user1.setAddress1("vvvvsd");
        user1.setAddress2("avvvvdad");

        userService.insertUser(user1);

        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user3);

        userService.insertUser(userList);

        userService.getAllUser();

//        userService.getUserById(Integer.toString(user1.getId()));
    }
}
