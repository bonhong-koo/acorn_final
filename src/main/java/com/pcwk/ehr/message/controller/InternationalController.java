package com.pcwk.ehr.message.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class InternationalController {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MessageSource messageSource;

	/**
	 * 
	 */
	public InternationalController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ InternationalController()                               │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@GetMapping(value = "/lang.do",produces ="text/plain;charset=UTF-8")
	@ResponseBody
	public String lang(@RequestParam(name = "lang", value = "lang")String lang) {
		String jsonString = "";
		
		log.debug("1.lang:{}",lang);
		
		Locale locale = (lang !=null ?new Locale(lang): Locale.getDefault());
		log.debug("2. locale:{}",locale);
		Object param[] = {"등록** "};
		messageSource.getMessage("message.common.registered", param, locale);
		log.debug("3. jsonString:{}",jsonString);
		
		
		return jsonString;
	}
	
	
	//http://localhost:8080/ehr/message/greet.do?lang=ko
	
	@GetMapping(value = "/greet.do",produces ="text/plain;charset=UTF-8")
	@ResponseBody
	public String greet(@RequestParam(name = "lang", value = "lang")String lang) {
		String jsonString = "";
		
		log.debug("1.lang:{}",lang);
		
		Locale locale = (lang !=null ?new Locale(lang): Locale.getDefault());
		log.debug("2. locale:{}",locale);
		Object param[] = {"등록** "};
		jsonString=messageSource.getMessage("message.common.registered", param, locale);
		log.debug("3. jsonString:{}",jsonString);
		
		
		return jsonString;
	}
	
}
