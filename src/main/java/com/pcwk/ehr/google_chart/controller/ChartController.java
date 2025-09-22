package com.pcwk.ehr.google_chart.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.pcwk.ehr.google_chart.domain.PizzaDTO;

@Controller
@RequestMapping("/google_chart")
public class ChartController {

	Logger log = LogManager.getLogger(getClass());

	public ChartController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ ChartController()                                       │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	
	
	//pieData.do
	@GetMapping(value = "pieData.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody 
	public String pieData() {
		String jsonString = "";

		PizzaDTO pizza01=new PizzaDTO("버섯",3);
		PizzaDTO pizza02=new PizzaDTO("양파",1);
		PizzaDTO pizza03=new PizzaDTO("올리브",1);
		PizzaDTO pizza04=new PizzaDTO("호박",1);
		PizzaDTO pizza05=new PizzaDTO("소시지",2);
		
		
		List<PizzaDTO> list=new ArrayList<PizzaDTO>();
		list.add(pizza01);
		list.add(pizza02);
		list.add(pizza03);
		list.add(pizza04);
		list.add(pizza05);
		
		JsonArray mainArray=new JsonArray();
		
		for(PizzaDTO dto :list) {
			JsonArray childArray=new JsonArray();
			
			childArray.add(dto.getTopping());
			childArray.add(dto.getSlices());
			
			
			mainArray.add(childArray);
		}
		jsonString = mainArray.toString();
		
		
		log.debug("jsonString:\n"+jsonString);
		
		return jsonString;
	}
	
	@GetMapping("/chartAll.do")
    public String chartAll() {
		String viewName = "google_chart/chartAll";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *chartAll()*                  │");
		log.debug("└───────────────────────────┘");		
		
		log.debug("viewName: {}",viewName);
		return viewName;		
	}
	
	@GetMapping("/line.do")
    public String line() {
		String viewName = "google_chart/line";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *line()*                  │");
		log.debug("└───────────────────────────┘");		
		
		log.debug("viewName: {}",viewName);
		return viewName;		
	}
	
	@GetMapping("/pie.do")
	public String pie() {
		String viewName = "google_chart/pie";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *pie()*                   │");
		log.debug("└───────────────────────────┘");		
		
		log.debug("viewName: {}",viewName);
		return viewName;
	}
	
}
