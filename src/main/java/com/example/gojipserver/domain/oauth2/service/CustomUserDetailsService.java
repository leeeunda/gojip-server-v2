package com.example.gojipserver.domain.oauth2.service;

import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
       return UserPrincipal.create(user);
    }
}
