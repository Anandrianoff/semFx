package service;

import form.UserRegistrationForm;
import model.User;

import java.util.List;

/**
 * Created by Andrey on 23.04.2017.
 */
public interface UserService {

    User saveNewUser(UserRegistrationForm form);

    User confirmUser(long id, String token);

    List<User> getAllUsers();

    void removeUser(long userId);

    void changeUserConfirmatiion(long userId);
}
