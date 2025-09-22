package com.pcwk.ehr.login.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.user.domain.UserDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	LoginService  loginService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	SessionLocaleResolver localeResolver;

	/**
	 * 
	 */
	public LoginController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ LoginController()                                       │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	  
	@GetMapping("/lang.do")
	public String lang(@RequestParam(name = "lang", value = "lang")String lang,
			Model model,
			HttpServletRequest request) {
		String viewNString = "login/login";
		
		log.debug("1.lang:{}",lang);
		
		Locale locale = (lang !=null ?new Locale(lang): Locale.getDefault());
		log.debug("2. locale:{}",locale);
		
		//SessionLocaleResolver
		localeResolver.setLocale(request, null, locale);
		model.addAttribute("lang", lang);
		
		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	}
	
	
	@GetMapping("/loginView.do")
	public String loginView() {
		String viewNString = "login/login";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *loginView()*             │");
		log.debug("└───────────────────────────┘");			
		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession httpSession) {
		String viewName = "main/main";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *logout()*                │");
		log.debug("└───────────────────────────┘");			
		
		
		if(null != httpSession.getAttribute("user")) {
			//session삭제
			httpSession.invalidate();
		}
		
		
		return viewName;
	}
	
	@PostMapping(value = "/login.do", produces = "text/plain;charset=UTF-8" )
	@ResponseBody
	public String login(UserDTO param, HttpSession httpSession) {
		String jsonString = "";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *login()*                 │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("1. param: {}",param);
		
		
		int flag = loginService.idPassCheck(param);
		
		String message = "";
		//flag->10 :id불일치
		//flag->20 :비번 불일치
		//flag->30 :id/비번 일치
		
		if( 10 == flag) {
			message = "아이디를 확인 하세요.";
		}else if( 20 == flag) {
			message = "비밀번호를 확인 하세요.";
		}else if( 30 == flag) {
			message = param.getUserId()+"님 환영 합니다.";
			UserDTO user=loginService.doSelectOne(param);
			log.debug("2. user: {}",user);
			//session 사용자 정보 
			httpSession.setAttribute("user", user);
		}
		
		jsonString =new Gson().toJson(new MessageDTO(flag, message));
		log.debug("3. jsonString: {}",jsonString);
		
		return jsonString;
	}
	
	
	
}
