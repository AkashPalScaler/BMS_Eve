package com.scaler.BMS_Eve.services;

import com.scaler.BMS_Eve.models.User;
import com.scaler.BMS_Eve.repositories.UserRepository;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User signup(String name, String email, String password){
        // Fetch user with email and check if user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        // if exists, throw exception
        if(optionalUser.isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        // else, save the user data using user repository
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        // return the user
        return userRepository.save(user);
    }
}
