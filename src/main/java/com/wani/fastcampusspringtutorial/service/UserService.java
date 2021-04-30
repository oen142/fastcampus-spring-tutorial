package com.wani.fastcampusspringtutorial.service;

import com.wani.fastcampusspringtutorial.model.User;
import com.wani.fastcampusspringtutorial.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User readUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

    public User updateUser(User user){
        return null;
    }

    public boolean deleteUser(User user){
        return false;
    }
}
