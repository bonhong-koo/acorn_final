package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class LoginControllerTest {
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	// 웹브라우저 대역 객체
	MockMvc mockMvc;
	
	@Autowired
	UserMapper mapper;
	
	UserDTO  userVO01;
	
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		userVO01= new UserDTO("pcwk01", "이상무01", "4321", "사용안함", 0, 0, Level.BASIC, "jamesol@paran.com");
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void login() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ login()                   │");
		log.debug("└───────────────────────────┘");		
		// 0. 전체 삭제
		// 1. userVO01 데이터 저장
		// 2. login호출				
		
		
	    // 0.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		// 1.
		int flag = mapper.doSave(userVO01);
		assertEquals(1,flag);
		assertEquals(1,mapper.getCount());
		
		//2.
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/login/login.do")
		     .param("userId", userVO01.getUserId())
		     .param("password", userVO01.getPassword());
		
		//2.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			.andExpect(status().isOk());
			
			
		//Session에서 사용자 정보 
		MockHttpSession seesion =(MockHttpSession) resultActions.andReturn()
				.getRequest().getSession();
		
		UserDTO user = (UserDTO) seesion.getAttribute("user");
		assertNotNull(user);
		log.debug("user:{}",user);
		
		//3.
		String returnBody =resultActions.andDo(print())
		   .andReturn()
		   .getResponse()
		   .getContentAsString();
		
		log.debug("returnBody:{}",returnBody);
		
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		assertEquals(30, resultMessage.getMessageId());
		assertEquals("pcwk01님 환영 합니다.", resultMessage.getMessage());
	}
	
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ beans()                   │");
		log.debug("└───────────────────────────┘");
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(mapper);		
		
		log.debug("webApplicationContext:{}",webApplicationContext);
		log.debug("mockMvc:{}",mockMvc);
		log.debug("mapper:{}",mapper);		
	}

}
