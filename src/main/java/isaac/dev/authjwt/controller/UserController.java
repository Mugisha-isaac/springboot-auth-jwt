package isaac.dev.authjwt.controller;

import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@RestController
public class UserController {
    @Autowired
     UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user")
    public List<User> getUsers(){
       return userService.getAllUsers();
    }

    @PostMapping("/user/register")
    public User createNewUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userService.addNewUser(user);
    }
}
