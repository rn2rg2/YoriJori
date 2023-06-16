package com.yorijori.foodcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;

@Service
public class LoginService {
    @Autowired
    private MemberRepository userRepository;

    public UserInfo loginUser(String userName, String userPassword) {
        return userRepository.findById(userName).orElse(null);
    }
}