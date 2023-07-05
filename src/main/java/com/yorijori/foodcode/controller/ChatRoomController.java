package com.yorijori.foodcode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.VO.UserForChatResponse;
import com.yorijori.foodcode.jpa.VO.UserForChatVO;
import com.yorijori.foodcode.jpa.entity.ChatInfo;
import com.yorijori.foodcode.jpa.entity.ChatMsg;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {
	ChatService chatService;

	@Autowired
	public ChatRoomController(ChatService chatService) {
		super();
		this.chatService = chatService;
	}

	@RequestMapping("/list")
	public String chat(Model model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		// 모든 채팅방 목록 반환
		List<ChatInfo> list = new ArrayList<ChatInfo>();
		list = chatService.findAllRoomByUserId(user);

		if (list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("chatId", list.get(0).getChatId());
			// List<UserForChatVO> chatinfo = new ArrayList<UserForChatVO>();
			List<UserForChatResponse> chatinfo = new ArrayList<UserForChatResponse>();
			for (ChatInfo info : list) {
				// user1 이 현재 로그인 사람과 동일한 경우
				UserForChatResponse res = new UserForChatResponse(info);
				if (info.getUser1id().equals(user.getUserId())) {
					res.setUserForChat(chatService.getUserInfoForChat(info.getUser2id()));
				} else {
					// user2 이 현재 로그인 사람과 동일한 경우
					res.setUserForChat(chatService.getUserInfoForChat(info.getUser1id()));
				}
				chatinfo.add(res);
			}
			model.addAttribute("chatinfo", chatinfo);
		}
		return "thymeleaf/mypage/chat";
	}

	// 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
//    // 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatInfo> room(HttpSession session) {
//    	UserInfo user = (UserInfo) session.getAttribute("userinfo");
//        return chatService.findAllRoomByUserId(user);
//    }
	// 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
	public ChatInfo createRoom(@RequestParam String userId2, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		return chatService.createRoom(user.getUserId(), userId2);
	}

//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable int roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
	// 특정 채팅방 조회
	@GetMapping("/room/{roomId}")
	@ResponseBody
	public List<ChatMsg> roomInfo(@PathVariable int roomId) {
		return chatService.findById(roomId);
	}
}