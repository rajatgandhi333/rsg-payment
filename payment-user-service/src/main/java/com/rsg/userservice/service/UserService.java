package com.rsg.userservice.service;

import com.rsg.userservice.config.JWTTokenProvider;
import com.rsg.userservice.model.User;
import com.rsg.userservice.model.UserRequest;
import com.rsg.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("User Already Exist");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public String authenticateUser(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            return jwtTokenProvider.createToken(userRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid Credentials");
        }
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username).get();
    }
}
