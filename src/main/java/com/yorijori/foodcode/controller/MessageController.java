package com.yorijori.foodcode.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.yorijori.foodcode.jpa.entity.ChatMsg;
import com.yorijori.foodcode.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations template;
    
    @Autowired
    ChatService chatservice;
    
    @MessageMapping(value="/chat/enter") //pub/chat/enter
    public void enter(ChatMsg message) {
    	System.out.println("=================================");
    	System.out.println(message.getSenderId() + "님 채팅 방 입장");
    	System.out.println("=================================");
    	//message.setMsg(message.getSenderId() + "님이 채팅방에 참여하였습니다.");
        //template.convertAndSend("/sub/chat/room/" + message.getChatId(), message);
    	//template.convertAndSend("/sub/chat/room/"+message.getChatId(),message);
    }

    @MessageMapping(value="/chat/message")
    public void message(ChatMsg message) {
    	message.setDate(LocalDateTime.now());
    	chatservice.insertMSG(message);
    	template.convertAndSend("/sub/chat/room/"+message.getChatId(),message);
    }
}