package com.rsg.userservice.model;

import lombok.Data;

@Data
public class UserResponse {

    private String token;

    public UserResponse(String token) {
        this.token = token;
    }
}
