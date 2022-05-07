package isaac.dev.authjwt.controller;

import isaac.dev.authjwt.exceptions.ResourceNotFoundException;
import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@RestController
public class UserController {
    @Autowired
     UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public List<User> getUsers(){
       return userService.getAllUsers();
    }

    @PostMapping("/user/register")
    public User createNewUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.addNewUser(user);
    }

    @GetMapping("/user/{id}")

    public ResponseEntity getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found on ::" + userId));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/")

    public ResponseEntity getUserByEmail(@RequestBody String email) throws ResourceNotFoundException{
        User user = userService.findByEmail(email);
        System.out.println(user);
        if(user == null){
            throw  new ResourceNotFoundException("user with " + email + " not found");
        }
        else{
            return ResponseEntity.ok().body(user);
        }

    }


}
