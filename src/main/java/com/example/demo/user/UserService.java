package com.example.demo.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        User userToAdd = new User();
        userToAdd.setLogin(user.getLogin());
        userToAdd.setFirstName(user.getFirstName());
        userToAdd.setLastName(user.getLastName());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        userToAdd.setPassword(encryptedPassword);
        List<UserRole> list = Collections.singletonList(new UserRole(userToAdd, Role.ROLE_USER));
        userToAdd.setRoles(new HashSet<>(list));

        try {
            userRepository.save(userToAdd);
            return true;
        } catch (Exception e){
//            System.out.println("bład");
//            throw (e);
            return false;
        }
    }

    public Optional<User> userProfile(String name) {
        Optional<User> userProfile = userRepository.findByLogin(name);
        return userProfile;
    }

    public boolean updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setLogin(user.getLogin());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        userToUpdate.setPassword(encryptedPassword);
        try {
            userRepository.save(userToUpdate);
            return true;
        } catch (Exception e){
//            System.out.println("bład");
//            throw (e);
            return false;
        }
    }
}
