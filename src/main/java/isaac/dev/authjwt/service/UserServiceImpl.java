package isaac.dev.authjwt.service;

import isaac.dev.authjwt.exceptions.CustomException;
import isaac.dev.authjwt.model.User;
import isaac.dev.authjwt.repository.UserRepository;
import isaac.dev.authjwt.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    public String signIn(String email, String password) {
        try{
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
             return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getUserRoles());
        }
        catch (AuthenticationException e){
            throw  new CustomException("Invalid email/password provided", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
