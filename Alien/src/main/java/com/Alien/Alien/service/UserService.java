package com.Alien.Alien.service;

import com.Alien.Alien.model.User;

public interface UserService {
    void registerUser(User user);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    public User findByEmail(String email);
}
