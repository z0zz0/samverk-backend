package com.samverk.service;

import com.samverk.domain.model.User;
import com.samverk.domain.repository.UserRepository;
import com.samverk.util.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(ErrorMessage.USER_NOT_FOUND));
    }

    public User createUser(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public User updateUser(UUID userId, User userDetails) {
        User user = getUserById(userId);
        user.setEmail(userDetails.getEmail());
        user.setPasswordHash(userDetails.getPasswordHash());
        user.setFirstName(userDetails.getFirstName());
        user.setSurname(userDetails.getSurname());
        user.setPhonenumber(userDetails.getPhonenumber());
        user.setAddress(userDetails.getAddress());
        user.setOrganization(userDetails.getOrganization());
        // Update other fields as necessary
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
