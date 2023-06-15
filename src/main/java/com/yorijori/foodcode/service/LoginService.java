package com.yorijori.foodcode.service;

import com.yorijori.foodcode.jpa.entity.MemberEntity;
import com.yorijori.foodcode.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private MemberRepository userRepository;

    public MemberEntity loginUser(String userName, String userPassword) {
        return userRepository.findById(userName).orElse(null);
    }
}