package com.example.demo.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.demo.user.User> userOptional = userRepository.findByLogin(username);

        if (userOptional.isPresent()) {
            com.example.demo.user.User user = userOptional.get();
            Set<SimpleGrantedAuthority> role = user.getRoles()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                    .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), role);
        }

        throw new UsernameNotFoundException("login" + username + "nie znaleziony");

    }
}
