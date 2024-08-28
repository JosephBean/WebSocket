package com.app.controller;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Controller
public class MessageController {

	@MessageMapping("/conn")
	@SendTo("/topic/bean")
	public Map<String, String> conn(Map<String, String> paramMap) {
		System.out.println("/conn");
		return paramMap;
	}
	
	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		System.out.println("Session Connect Event");
	}
	
}
