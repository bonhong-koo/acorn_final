package com.pcwk.ehr.realtime.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.user.domain.UserDTO;

@Controller
public class RealTimeController {

	Logger log = LogManager.getLogger(getClass());

	/**
	 * 
	 */
	public RealTimeController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ RealTimeController()                                    │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@GetMapping("/doRealTimeView2.do")
	public String doSaveView2() {
		String viewNString = "realtime/realtime2";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRealTimeView()*        │");
		log.debug("└───────────────────────────┘");	

		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	} 
	
	
	@GetMapping("/doRealTimeView.do")
	public String doSaveView() {
		String viewNString = "realtime/realtime";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRealTimeView()*        │");
		log.debug("└───────────────────────────┘");	

		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	}   
    @MessageMapping("/post") // @PostMapping처럼 생각하면 됨
    @SendTo("/topic/post") // 메시지를 "/topic/greetings"를 구독 중인 클라이언트에 보냄
	public String post(BoardDTO message) {
		log.debug("message: {}",message);
		String jsonString = new Gson().toJson(message)  ;
		
		log.debug("jsonString: {}",jsonString);
		return jsonString;
	}
    
    
	
	// 클라이언트가 "/ehr/hello"로 보낸 메시지를 처리
    @MessageMapping("/greetings") // @PostMapping처럼 생각하면 됨
    @SendTo("/topic/greetings") // 메시지를 "/topic/greetings"를 구독 중인 클라이언트에 보냄
	public String greet(HelloMessage message) {
		log.debug("message: {}",message.getName());
		String jsonString = new Gson().toJson(new Greeting("Hello, " + message.getName() + "!"))  ;
		
		log.debug("jsonString: {}",jsonString);
		return jsonString;
	}
	
    // DTO 클래스
    public static class HelloMessage {
        private String name;
        // getter/setter
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class Greeting {
        private String content;
        public Greeting(String content) { this.content = content; }
        public String getContent() { return content; }
        
		@Override
		public String toString() {
			return "Greeting [content=" + content + "]";
		}
        
        
    }	
	
	@MessageMapping("/hello")        // 클라이언트가 "/ehr/hello"로 보낼 경우 매핑됨
	@SendTo("/topic/notice")         // 응답 메시지를 "/topic/notice"로 브로드캐스트
	public String greet(UserDTO param) {
		log.debug("param: {}",param);
	    return "Hello, " + param.getName();
	}
	
}
