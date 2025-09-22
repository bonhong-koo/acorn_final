package com.pcwk.ehr.except.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.user.domain.UserDTO;

@Controller
@RequestMapping("/except")
public class SampleController {

	Logger log = LogManager.getLogger(getClass());

	/**
	 * 
	 */
	public SampleController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ SampleController()                                      │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@GetMapping("/nullPoint.do")
	public String nullPoint(UserDTO user) {
		String viewName = "main/menu";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *nullPoint()*             │");
		log.debug("└───────────────────────────┘");	
		
		if(null==user.getUserId() || "".equals(user.getUserId())) {
			log.debug("user:"+user);
			throw new NullPointerException("아이디를 입력 하세요.");
		}
		
		
		
		return viewName;
	}
	
	@GetMapping("/arithmetic.do")
	public String arithmetic() {
		String viewName = "main/menu";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *arithmetic()*            │");
		log.debug("└───────────────────────────┘");	
		
		try {
			
			int age = 22;
			int newAge = age/0;
			
			
		}catch(ArithmeticException e) {
			log.debug("│ *ArithmeticException()*            │"+e.getMessage());
			throw new ArithmeticException("arithmetic:"+e.getMessage());
		}
		
		
		return viewName;
	}
	
	
	
}
