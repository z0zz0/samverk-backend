package com.samverk.domain.service;

import com.samverk.domain.entity.User;
import com.samverk.domain.repository.UserRepository;
import com.samverk.util.Log;
import com.samverk.util.ErrorMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserDomainService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDomainService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getAllUsers() {
        Log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        Log.info("Fetching user with id: " + userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    Log.error(ErrorMessage.USER_NOT_FOUND);
                    return new RuntimeException(ErrorMessage.USER_NOT_FOUND);
                });
    }

    @Transactional
    public User createUser(User user) {
        Log.info("Creating new user");
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(UUID userId, User userDetails) {
        Log.info("Updating user with id: " + userId);
        User user = getUserById(userId);
        user.setEmail(userDetails.getEmail());
        user.setPasswordHash(bCryptPasswordEncoder.encode(userDetails.getPasswordHash()));
        user.setFirstName(userDetails.getFirstName());
        user.setSurname(userDetails.getSurname());
        user.setPhonenumber(userDetails.getPhonenumber());
        user.setAddress(userDetails.getAddress());
        user.setOrganization(userDetails.getOrganization());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        Log.info("Deleting user with id: " + userId);
        userRepository.deleteById(userId);
    }
}
