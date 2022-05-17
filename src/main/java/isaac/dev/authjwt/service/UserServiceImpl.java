package isaac.dev.authjwt.service;

import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
   @Autowired
   private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
      return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User addNewUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }


    @Override
    public void deleteUser(Long userId) {
      userRepository.deleteById(userId);
    }

}
