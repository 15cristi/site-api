package com.parcare.parcare_sistem.service;

import com.parcare.parcare_sistem.model.User;
import com.parcare.parcare_sistem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilizatorul nu există: " + username);
        }

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // corect
                .build();
    }
}
