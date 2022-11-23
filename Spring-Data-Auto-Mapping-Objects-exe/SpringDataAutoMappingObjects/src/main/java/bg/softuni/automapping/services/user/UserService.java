package bg.softuni.automapping.services.user;

import bg.softuni.automapping.entities.User;
import bg.softuni.automapping.entities.dtos.UserDTO;

import java.util.Optional;

public interface UserService {

    String registerUser(UserDTO userDTO);
    String  loginUser(String email, String pass);

    String logoutUser();

    Optional<User> getByEmail(String email,String password);

    User getUserLoggedIn(boolean LogIn);

    void checkIsAdministratorLoggedIn();
    User persistUser (User user);
}
