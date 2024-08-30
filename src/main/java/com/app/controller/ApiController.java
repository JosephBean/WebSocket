package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ChannelDTO;
import com.app.mapper.ChannelMapper;

@RestController
public class ApiController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ChannelMapper channelMapper;

	@GetMapping("/")
	public String home() {
		return "Web Socket Start!!";
	}
	
	@GetMapping("/test")
	public boolean test() {
		String msg = "확인!";
		simpMessagingTemplate.convertAndSend("/topic/bean", msg);
		return true;
	}
	
	@PostMapping("/getChannel")
	public List<ChannelDTO> getChannel() {
		return channelMapper.getChannel();
	}
	
}
