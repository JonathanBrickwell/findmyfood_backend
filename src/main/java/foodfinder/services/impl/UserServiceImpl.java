package foodfinder.services.impl;

import foodfinder.dto.User;
import foodfinder.repository.UserRepository;
import foodfinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> fetchUserInfo(String userName, String userSurname) {

        return null;
    }

    public User fetchUserById(Integer userId) {

        if (userId != 0) {

            return userRepository.findUserByUserId(userId);
        }
        return null;
    }

    public void userDelete(Integer userId) {

        userRepository.deleteById(userId);
    }

    public User createUser(User user) {

        user.setPassword(passHashed(user.getPassword()));

        return userRepository.save(user);
    }

    public void updateUserPassword(String password, Integer userId) {


        userRepository.updateUserPassword(userId, password);
    }

    public void updateUserEmail(String mail, Integer userId) {

        userRepository.updateUserMail(userId, mail);
    }

    public void updateUserNameAndSurname(User user, Integer userId) {

        User userDb = userRepository.findUserByUserId(userId);

        if (user.getName() != null) {

            userDb.setName(user.getName());

        }
        if (user.getSurname() != null) {

            userDb.setSurname(user.getSurname());

        }
        userRepository.save(user);
    }

    private String passHashed(String password) {


        return passwordEncoder.encode(password);
    }

}