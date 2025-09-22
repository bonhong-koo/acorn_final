package com.pcwk.ehr.login;

import static com.pcwk.ehr.user.service.UserService.MIN_LOGIN_COUNT_FOR_SILVER;
import static com.pcwk.ehr.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;
import com.pcwk.ehr.user.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class LoginServiceTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ApplicationContext context;

	@Autowired
	UserMapper mapper;

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	List<UserDTO> users;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		users = Arrays.asList(
				// BASIC 그대로
				// BASIC -> SILVER
				// SILVER 그대로
				// SILVER -> GOLD
				// GOLD
				new UserDTO("pcwk01", "이상무01", "4321", "사용안함", MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, Level.BASIC,
						"jamesol@paran.com"),
				new UserDTO("pcwk02", "이상무02", "4321", "BASIC -> SILVER", MIN_LOGIN_COUNT_FOR_SILVER, 0, Level.BASIC,
						"jamesol@paran.com"),
				new UserDTO("pcwk03", "이상무03", "4321", "사용안함", MIN_LOGIN_COUNT_FOR_SILVER + 10,
						MIN_RECOMMEND_FOR_GOLD - 1, Level.SILVER, "jamesol@paran.com"),
				new UserDTO("pcwk04", "이상무04", "4321", "SILVER -> GOLD", MIN_LOGIN_COUNT_FOR_SILVER + 11,
						MIN_RECOMMEND_FOR_GOLD, Level.SILVER, "jamesol@paran.com"),
				new UserDTO("pcwk05", "이상무05", "4321", "사용안함", MIN_LOGIN_COUNT_FOR_SILVER + 40,
						MIN_RECOMMEND_FOR_GOLD + 7, Level.GOLD, "jamesol@paran.com"));
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Test
	void idPassCheck() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *idPassCheck()*           │");
		log.debug("└───────────────────────────┘");
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		// 2.1 등록건수 비교
		// 3. 단건조회
		// 3.1 등록데이터 비교

		// 4.아이디 비번 비교 : 30(id비번 일치)

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		String rawPassword = users.get(0).getPassword();
		UserDTO inVO = users.get(0);
		userService.doSave(inVO);
		assertEquals(1, mapper.getCount());

		// 3.
		UserDTO outVO = mapper.doSelectOne(inVO);
		log.debug("outVO:{}", outVO);

		boolean isMatch = passwordEncoder.matches(rawPassword, outVO.getPassword());
		log.debug("isMatch:{}", isMatch);
		assertTrue(isMatch);

		// 4.
		// rawPasword로 수정
		outVO.setPassword(rawPassword);

		// outVO.setUserId(outVO.getUserId()+"99");
		int flag = loginService.idPassCheck(outVO);
		log.debug("flag:{}", flag);
		assertEquals(30, flag);

	}

	@Disabled
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(mapper);
		assertNotNull(loginService);
		assertNotNull(userService);
		assertNotNull(passwordEncoder);

		log.debug("context:" + context);
		log.debug("mapper:" + mapper);
		log.debug("loginService:" + loginService);
		log.debug("userService:" + userService);
		log.debug("passwordEncoder:" + passwordEncoder);
	}

}
