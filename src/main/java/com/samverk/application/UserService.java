package com.samverk.application;

import com.samverk.domain.entity.User;
import com.samverk.domain.service.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserDomainService userDomainService;

    public UserService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    public List<User> getAllUsers() {
        return userDomainService.getAllUsers();
    }

    public User getUserById(UUID userId) {
        return userDomainService.getUserById(userId);
    }

    public User createUser(User user) {
        return userDomainService.createUser(user);
    }

    public User updateUser(UUID userId, User userDetails) {
        return userDomainService.updateUser(userId, userDetails);
    }

    public void deleteUser(UUID userId) {
        userDomainService.deleteUser(userId);
    }
}