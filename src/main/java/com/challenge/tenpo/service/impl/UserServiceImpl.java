package com.challenge.tenpo.service.impl;

import com.challenge.tenpo.entity.User;
import com.challenge.tenpo.helper.TenpoChallengeException;
import com.challenge.tenpo.model.UserModel;
import com.challenge.tenpo.repository.UserRepository;
import com.challenge.tenpo.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void singUp(UserModel userModel) {

        if(userRepository.existsByUserName(userModel.getUserName())){
            throw new TenpoChallengeException("UserName already exists");
        }

        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setUserName(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(java.nio.CharBuffer.wrap(userModel.getPassword())));

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found for userName: " + userName));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new HashSet<>());
    }
}
