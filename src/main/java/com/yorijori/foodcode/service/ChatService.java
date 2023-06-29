package com.yorijori.foodcode.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.ChatInfo;
import com.yorijori.foodcode.jpa.entity.ChatMsg;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.ChatInfoRepository;
import com.yorijori.foodcode.jpa.repository.ChatMsgRepository;

@Service
public class ChatService {

	// private Map<String, ChatInfo> chatRooms;
	ChatInfoRepository chatinforepo;
	ChatMsgRepository chatmsgrepo;

//    @PostConstruct
//    //의존관게 주입완료되면 실행되는 코드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }

	@Autowired
	public ChatService(ChatInfoRepository chatinforepo, ChatMsgRepository chatmsgrepo) {
		super();
		this.chatinforepo = chatinforepo;
		this.chatmsgrepo = chatmsgrepo;
	}

	// 채팅방 불러오기
	public List<ChatInfo> findAllRoomByUserId(UserInfo user) {
		// 채팅방 최근 생성 순으로 반환
		List<ChatInfo> result = new ArrayList<>();
		result = chatinforepo.findAllRoomUserId(user.getUserId());
		System.out.println("=================================");
		System.out.println(result);
		System.out.println("=================================");
		if (result.size() > 0) {
			Collections.reverse(result);
		}
		// List<ChatInfo> result = new ArrayList<>(chatRooms.values());

		return result;
	}

	// 채팅방 하나 불러오기
	public List<ChatMsg> findById(int roomId) {
		return chatmsgrepo.findAllByChatId(roomId);
	}

	// 채팅방 생성
	public ChatInfo createRoom(String userId, String userId2) {
		ChatInfo dto = new ChatInfo();
		dto.setUser1id(userId);
		dto.setUser2id(userId2);
		return chatinforepo.save(dto);
	}
	
	//메세지 저장
	public ChatMsg insertMSG(int chatId, String senderId, String msg) {
		ChatMsg dto = new ChatMsg();
		dto.setChatId(chatId);
		dto.setSenderId(senderId);
		dto.setMsg(msg);
		return chatmsgrepo.save(dto);
	}

}
