package com.pcwk.ehr.template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

	Logger log = LogManager.getLogger(getClass());


	public TemplateController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ TemplateController()                                    │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@GetMapping("/reg_boot_horizontal.do")
	public String regBootHorizontal() {
		String viewName = "template/reg_boot_horizontal";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *reg_boot_horizontal()*   │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}
	
	@GetMapping("/reg_boot.do")
	public String regBoot() {
		String viewName = "template/reg_boot";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *regBoot()*               │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}
	
	
	
	@GetMapping("/list_boot.do")
	public String listBoot() {
		String viewName = "template/list_boot";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *listBoot()*              │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}
	
	
	@GetMapping("/mng.do")
	public String mng() {
		String viewName = "template/mng";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *mng()*                  │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}
	
	@GetMapping("/reg.do")
	public String reg() {
		String viewName = "template/reg";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *reg()*                  │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}

	
	
	@GetMapping("/list.do")
	public String list() {
		String viewName = "template/list";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *list()*                  │");
		log.debug("└───────────────────────────┘");	
		
		log.debug("viewName: {}",viewName);
		
		return viewName;
		
	}
	 
	
	
}
