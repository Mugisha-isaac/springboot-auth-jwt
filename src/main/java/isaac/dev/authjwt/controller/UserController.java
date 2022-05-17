package isaac.dev.authjwt.controller;

import isaac.dev.authjwt.exceptions.ResourceNotFoundException;
import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User newUser) throws ResourceNotFoundException{
        User user = userService.getUserById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        final User updatedUser = userService.addNewUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception{
        User user = userService.getUserById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        userService.deleteUser(userId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
