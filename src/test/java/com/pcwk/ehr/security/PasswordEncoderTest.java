package com.pcwk.ehr.security;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class PasswordEncoderTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext context;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
				
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
				
	}
	
	@Test
	void bCryptPassword() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *bCryptPassword()*        │");
		log.debug("└───────────────────────────┘");	
		
		//암호화
		String rawPassword01 = "4321";
		
		
		String encodedPassowrd = passwordEncoder.encode(rawPassword01);
		log.debug("rawPassword01: {}",rawPassword01);
		log.debug("encodedPassowrd: {}",encodedPassowrd);
		log.debug("encodedPassowrd.length: {}",encodedPassowrd.length());
		
		
		//검증
		boolean isMatch = passwordEncoder.matches(rawPassword01, encodedPassowrd);
		log.debug("isMatch: {}",isMatch);		
		
	}

	@Disabled
	@Test
	void beans() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *beans()*                 │");
		log.debug("└───────────────────────────┘");		
		assertNotNull(context);
		assertNotNull(passwordEncoder);
		
		log.debug("context: {}",context);
		log.debug("passwordEncoder: {}",passwordEncoder);

	}

}
