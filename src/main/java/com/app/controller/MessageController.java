package com.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.app.dto.PayloadMessageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	// "/msg/conn" >> 접속
	@MessageMapping("/conn")
	@SendTo("/topic/bean")
	public Map<String, String> conn(Map<String, String> paramMap) {
		log.info("Path : {}", "/conn");
		return paramMap;
	}
	
	// "/msg/room"
	@MessageMapping("/room")
	public void conn1(@Payload PayloadMessageDTO payloadDTO, SimpMessageHeaderAccessor headerAccessor) {
		log.info("Path : {}", "/room");
		String sessionId = headerAccessor.getSessionId();
		log.info("SessionID : {}", sessionId);
		String topic = payloadDTO.getMessage().getTopic();
		payloadDTO.getMessage().setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		if(topic != null) {
			log.info("Payload : {}", payloadDTO);
			simpMessagingTemplate.convertAndSend(topic, payloadDTO);
		}
	}
	
	// "/msg/set"
	@MessageMapping("/set")
	// "/topic/get"
	@SendTo("/topic/get")
	public String conn2(String msg) {
		log.info("Message : {}", msg);
		return "성공";
	}
	
	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		System.out.println("Session Connect Event");
		System.out.println(event);
		// STOMP 헤더 접근기
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        // 클라이언트가 연결한 Endpoint를 가져오기
        String sessionId = accessor.getSessionId();
        // 연결 정보를 출력
        System.out.println("Client connected with sessionId: " + sessionId);
	}
	
	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		System.out.println("Session Disconnect Event");
	}
	
}
