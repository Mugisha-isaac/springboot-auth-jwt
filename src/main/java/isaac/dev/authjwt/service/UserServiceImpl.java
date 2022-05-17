package isaac.dev.authjwt.service;

import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final AuthenticationManager authenticationManager;

   @Autowired
   private UserRepository userRepository;

    public UserServiceImpl() {
        authenticationManager = null;
    }

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

    @Override
    public String signIn(String email, String password) {
        try{
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
             return 
        }
    }

}
