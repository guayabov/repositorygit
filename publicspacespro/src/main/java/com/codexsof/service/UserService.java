package com.codexsof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.codexsof.repository.*;
import com.codexsof.model.Users;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users register(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); 
        return userRepository.save(user);
    }

    public Users login(String username, String password) {
        Optional<Users> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; 
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
