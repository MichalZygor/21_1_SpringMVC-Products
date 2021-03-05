package com.example.demo.user;

import org.hibernate.TransactionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
}
