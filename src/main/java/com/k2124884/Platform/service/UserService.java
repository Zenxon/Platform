/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.service;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.User;
import com.k2124884.Platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null && user.getPassword().equals(password);
    }
}