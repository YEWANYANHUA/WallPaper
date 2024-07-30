package com.example.back.service;

import com.example.back.pojo.User;

public interface UserService {
    User loginService(String username,String password);
    User registService(User user);
}
