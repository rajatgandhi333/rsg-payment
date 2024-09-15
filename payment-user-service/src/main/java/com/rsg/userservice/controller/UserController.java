package com.rsg.userservice.controller;

import com.rsg.userservice.Util.Constants;
import com.rsg.userservice.config.JWTTokenProvider;
import com.rsg.userservice.model.User;
import com.rsg.userservice.model.UserRequest;
import com.rsg.userservice.model.UserResponse;
import com.rsg.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rsg.userservice.Util.Constants.LOGIN_USER;

@RestController
@RequestMapping(Constants.USER_API)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @PostMapping(Constants.REGISTER_USER_API)
    public ResponseEntity<User> registeruser(@RequestParam String username, @RequestParam String password) {
        User user = userService.registerUser(username, password);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(LOGIN_USER)
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
        String token = userService.authenticateUser(userRequest);
        return new ResponseEntity<>(new UserResponse(token), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
