package isaac.dev.authjwt.service;

import isaac.dev.authjwt.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User addNewUser(User user);
    void updateUser(Long userId,User newUser);
//    void deleteUser(Long userId);
}
