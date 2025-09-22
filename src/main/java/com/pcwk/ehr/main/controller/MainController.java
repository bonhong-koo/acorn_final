package com.pcwk.ehr.main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

	Logger log = LogManager.getLogger(getClass());

	public MainController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ MainController()                                        │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@GetMapping("/menu.do")
	public String menu() {
		String viewName = "main/menu";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *menu()*                  │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		return viewName;				
	}
	
	@GetMapping("/main.do")
	public String main() {
		String viewName = "main/main";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *menu()*                  │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		return viewName;				
	}	
	

}
