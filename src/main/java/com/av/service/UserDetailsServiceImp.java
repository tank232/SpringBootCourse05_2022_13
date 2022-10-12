package com.av.service;


import com.av.db.User;
import com.av.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(s).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getLogin());
        builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        user.getRole()
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
        builder.authorities(authorities);
        return builder.build();
    }
}
