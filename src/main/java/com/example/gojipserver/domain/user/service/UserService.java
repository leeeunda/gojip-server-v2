package com.example.gojipserver.domain.user.service;

import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
