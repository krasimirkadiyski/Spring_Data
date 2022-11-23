package bg.softuni.automapping.services.user;

import bg.softuni.automapping.entities.User;
import bg.softuni.automapping.entities.dtos.UserDTO;
import bg.softuni.automapping.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static bg.softuni.automapping.constants.Constants.EMAIL_REGEX;
import static bg.softuni.automapping.constants.Constants.PASS_REGEX;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    @Override
    public String registerUser(UserDTO userDTO) {
        validateUser(userDTO);
        User user = mapper.map(userDTO, User.class);
        if (userRepository.count() == 0) {
            user.setAdministrator(true);
        }
        persistUser(user);
        return user.getFullName();
    }
    @Override
    public String loginUser(String email, String pass) {
        Optional<User> userOptional = getByEmail(email, pass);
        if(userOptional.isEmpty()){
            throw new IllegalArgumentException("Incorrect username / password");
        }
        User user = userOptional.get();
        user.setLoggedIn(true);
        persistUser(user);
        return user.getFullName();
    }

    @Override
    public String logoutUser() {
        User user= getUserLoggedIn(true);
        user.setLoggedIn(false);
        persistUser(user);
        return user.getFullName();
    }


    @Override
    public Optional<User> getByEmail(String email,String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User getUserLoggedIn(boolean LogIn) {
        Optional<User> optionalUser = userRepository.findByLoggedIn(LogIn);
        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("No user was logged in.");
        }
        return optionalUser.get();
    }

    @Override
    public void checkIsAdministratorLoggedIn() {
        User user = getUserLoggedIn(true);

        Boolean administrator = user.getAdministrator();
        if (!administrator){
            throw new IllegalArgumentException("You don`t have permission to do that.");
        }
    }


    public User persistUser(User user) {
       return userRepository.save(user);
    }



    public boolean validateUser(UserDTO userDTO) {
        boolean isUserExist = userRepository.existsUserByEmail(userDTO.getEmail());
        if (isUserExist){
            throw new IllegalArgumentException("User already exists");
        }
        if (!Pattern.matches(EMAIL_REGEX,userDTO.getEmail())){
            throw new IllegalArgumentException("Incorrect format of email.");
        }
        if(!Pattern.matches(PASS_REGEX, userDTO.getPassword())){
            throw new IllegalArgumentException(("Incorrect format of password"));
        }
        if (!Objects.equals(userDTO.getPassword(), userDTO.getRepass())){
            throw new IllegalArgumentException("Passwords don't match.");
        }
        return true;
    }


}
