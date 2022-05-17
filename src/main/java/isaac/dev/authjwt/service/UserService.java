package isaac.dev.authjwt.service;

import isaac.dev.authjwt.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User addNewUser(User user);
    User findByEmail(String email);
    void deleteUser(Long userId);

    String signIn(String email,String password);

//    void deleteUser(Long userId);
}
