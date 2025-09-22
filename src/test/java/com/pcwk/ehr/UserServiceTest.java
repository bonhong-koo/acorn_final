/**
 * Package Name : com.pcwk.ehr.user <br/>
 * 파일명: UserServiceTest.java <br/>
 */
package com.pcwk.ehr;

import static com.pcwk.ehr.user.service.UserService.MIN_LOGIN_COUNT_FOR_SILVER;
import static com.pcwk.ehr.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import com.pcwk.ehr.cmn.AESUtil;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.GradeRationDTO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;
import com.pcwk.ehr.user.service.UserService;

/**
 * @author user
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context-test.xml"
})
class UserServiceTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	AESUtil aesUtil;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ApplicationContext context;

	@Autowired
	UserService userService;

	@Autowired
	UserMapper mapper;

	List<UserDTO> users;
	
	@Autowired
	DataSource dataSource;
	

	@Autowired
	PlatformTransactionManager   transactionManager;
	
	@Qualifier("dummyMailService") //bean id로 특정 빈 주입
	@Autowired
	MailSender mailSender;
	
	SearchDTO search;
	
	String rawPassword;
	String rawName;
	
	
	GradeRationDTO gradeRationDTO;
	
	
//	@Autowired
//	TestUserService  testUserService;
	
	/**
	 * @throws java.lang.Exception
	 */
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
		
		search = new SearchDTO();
		rawPassword = "4321";
		rawName     = "이상무";
		
		gradeRationDTO = new GradeRationDTO();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
    
	@Disabled
	@Test
	void yearGradeRation() throws SQLException {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *yearGradeRation()*       │");
		log.debug("└───────────────────────────┘");		
		//메서드 실행시 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 다건등록
		
		//1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		//2.
		int count = mapper.saveAll();
		log.debug("count:"+count);
		assertEquals(502, count);
		
		//2025년도 설정
		gradeRationDTO.setYear(2025);
		
		List<GradeRationDTO> list =this.userService.yearGradeRation(gradeRationDTO);
		for(GradeRationDTO dto :list) {
			log.debug("dto:"+dto);
		}
		
	}
	
	
	@Disabled
	@Test
	void doRetrieve() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");		
		
		//메서드가 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 다건등록
		//3. paging조회
		
		//1.
		mapper.deleteAll();		
		assertEquals(0, mapper.getCount());			
	
		assertEquals(0, mapper.getCount());	
		
		//2.
		int count = mapper.saveAll();
		log.debug("count:"+count);
		
		assertEquals(502, count);		
		
		//3.
		//paging
		search.setPageSize(10);
		search.setPageNo(1);
		
		//검색
		search.setSearchDiv("");
		//search.setSearchWord("이상무");
		
		//3.
		List<UserDTO> list= userService.doRetrieve(search);
		for(UserDTO vo :list) {
		    assertEquals(rawName, vo.getName());
		    boolean isMatch = passwordEncoder.matches(rawPassword, vo.getPassword());
		    assertTrue(isMatch);
			log.debug(vo);
		}
		
		assertEquals(10, list.size());
		
	}
	
	@Disabled
	@Test
	void doUpdate() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*              │");
		log.debug("└───────────────────────────┘");
		//메서드가 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록건수 비교
		//3. 단건조회
		//3.1 등록데이터 비교
		
		//4. 단건조회 데이터 수정
		//5. 수정
		//6. 수정 조회	
		//7. 4 비교 6
		
		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());		
		
		//mapper 저장시 param에 password,name 필드도 update(암호화 된다.)
		String rawName = users.get(1).getName();
		log.debug("rawName: {}",rawName);
		
		String rawPassword = users.get(1).getPassword();
		log.debug("rawPassword: {}",rawPassword);
		
		// 2.:userService
		int flag =  this.userService.doSave(users.get(1));
		
		assertEquals(1, flag);	
		assertEquals(1, mapper.getCount());
		
		// 3.:userService
		UserDTO outVO = userService.doSelectOne(users.get(1));
		log.debug("outVO:{}",outVO);
		
		users.get(1).setName(rawName);
		log.debug("users.get(1):{}",users.get(1));
		
		isSameUser(outVO, users.get(1));
		
        //---------------------------------		
        // 단방향 암호화 비번 비교
		//---------------------------------
		boolean isMatch = passwordEncoder.matches(rawPassword, outVO.getPassword());
		log.debug("isMatch:{}",isMatch);
		assertTrue(isMatch);		
		
		
		//4. 
		String upString = "_U";
		int    upInt    = 999;
		
		outVO.setName(outVO.getName()+upString);
		outVO.setPassword(rawPassword+upString);
		outVO.setLogin(outVO.getLogin()+upInt);
		outVO.setRecommend(outVO.getRecommend()+upInt);
		outVO.setGrade(outVO.getGrade().GOLD);
		outVO.setEmail(outVO.getEmail()+upString);
		
		//name 필드도 update(암호화 된다.)
		log.debug("1. outVO:"+outVO);
		rawName = outVO.getName();
		
		rawPassword = outVO.getPassword();
		
		//5.
		flag = userService.doUpdate(outVO);
		assertEquals(1, flag);
		log.debug("2. outVO:"+outVO);
		outVO.setName(rawName);
		
		//6. 
		UserDTO upVO = userService.doSelectOne(outVO);
		
		//비번 비교
		isMatch = passwordEncoder.matches(rawPassword, upVO.getPassword());
		assertTrue(isMatch);
		
		
		
		log.debug("upVO:"+upVO);
		isSameUser(outVO, upVO);
	}
	
	
	@Disabled
	@Test
	void upgradeAllOrNothing() throws SQLException {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *upgradeAllOrNothing()*   │");
		log.debug("└───────────────────────────┘");
		// 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제
		// 2.5명 사용자 모두 입력
		// 3.등업
		// 3.1 예외

		try {

			// 1.
			mapper.deleteAll();
			assertEquals(0, mapper.getCount());

			// 2.
			for (UserDTO dto : users) {
				mapper.doSave(dto);
			}
			assertEquals(5, mapper.getCount());

			// 3.
			//testUserService.upgradeLevels();

		} catch (Exception e) {
			log.debug("┌───────────────────────────┐");
			log.debug("│ Exception                 │" + e.getMessage());
			log.debug("└───────────────────────────┘");
		}

		// 트랜잭션 적용이 않되고 있음!
		checkLevel(users.get(1), false);// 등업

	}
	
	@Disabled
	@Test
	void passwordEncodeDoUpdate() throws Exception {
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록건수 비교
		//3. 단건조회
		//3.1 등록데이터 비교
		
		//4. 단건조회 데이터 수정		
		//5. 수정
		//6. 수정 조회		
		//7. 4 비교 6
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *passwordEncodeDoUpdate()*│");
		log.debug("└───────────────────────────┘");
		
		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());			
		
		String rawPassword = users.get(1).getPassword();
		log.debug("rawPassword: {}",rawPassword);
		
		// 2.
		int flag =  this.userService.doSave(users.get(1));
		
		assertEquals(1, flag);	
		assertEquals(1, mapper.getCount());
		
		
		// 3.
		UserDTO outVO = mapper.doSelectOne(users.get(1));
		log.debug("outVO:{}",outVO);
		boolean isMatch = passwordEncoder.matches(rawPassword, outVO.getPassword());
		log.debug("isMatch:{}",isMatch);
		assertTrue(isMatch);		
		
		//4.5.
		rawPassword = rawPassword;
		log.debug("rawPassword: {}",rawPassword);
		outVO.setPassword(rawPassword);
		flag = userService.doUpdate(outVO);
		assertEquals(1, flag);
		
		//6.
		UserDTO upVO=mapper.doSelectOne(outVO);
		log.debug("upVO:{}",upVO);
		
		isMatch = passwordEncoder.matches(rawPassword, upVO.getPassword());
		assertTrue(isMatch);
	}
	
	@Disabled
	@Test
	void nameEncodeDoSave() throws Exception {
		// 메서드는 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제		
		// 2.사용자 등록: 이름 암호화
		// 3.조회         : 이름 복호화
		// 4.비교
		log.debug("┌───────────────────────────┐");
		log.debug("│ *nameEncodeDoSave()*      │");
		log.debug("└───────────────────────────┘");
		
		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());		
		
		// 2.
		String rawName = users.get(4).getName();
		log.debug("rawName: {}",rawName);
		
		UserDTO inVO = users.get(4);

		
		//양방향 암호화 
		int flag = userService.doSave(inVO);
		assertEquals(1, flag);	
		assertEquals(1, mapper.getCount());	
		
		//3.
		UserDTO outVO = userService.doSelectOne(inVO);
		log.debug("outVO:{}",outVO);
	    
		//4. 이름 복호화		
		assertEquals(rawName, outVO.getName());
		
		//5. 비교:
		users.get(4).setName(rawName);

		isSameUser(users.get(4),outVO);
		log.debug("outVO:{}",outVO);
		log.debug("users.get(4):{}",users.get(4));
	}
	
	// 데이터 비교
	public void isSameUser(UserDTO outVO, UserDTO dto01) {
		assertEquals(outVO.getUserId(), dto01.getUserId());
		assertEquals(outVO.getName(), dto01.getName());
		//비번은 단방향 이므로 테스트 메서드에서 비교 할것.
		//assertEquals(outVO.getPassword(), dto01.getPassword());
		assertEquals(outVO.getLogin(), dto01.getLogin());
		assertEquals(outVO.getRecommend(), dto01.getRecommend());
		assertEquals(outVO.getGrade(), dto01.getGrade());
		assertEquals(outVO.getEmail(), dto01.getEmail());
	}
	
	
	
	//@Disabled
	@Test
	void passwordEncodeDoSave() throws Exception {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제
		// 2.등급있는 사용자 등록
		// 3.조회
		// 4.비교		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *passwordEncodeDoSave()*  │");
		log.debug("└───────────────────────────┘");
		
		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());	
		
		// 2.
		String rawPassword = users.get(4).getPassword();
		log.debug("rawPassword: {}",rawPassword);
		
		UserDTO userWithLevel = users.get(4);
		userService.doSave(userWithLevel);
		assertEquals(1, mapper.getCount());		
	
		// 3.
		UserDTO outVO = mapper.doSelectOne(userWithLevel);
		log.debug("outVO:{}",outVO);
		
		boolean isMatch = passwordEncoder.matches(rawPassword, outVO.getPassword());
		log.debug("isMatch:{}",isMatch);
		assertTrue(isMatch);
	}

	@Disabled
	@Test
	public void doSave() throws Exception {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제
		// 2.등급있는 사용자 등록
		// 2.등급없는 사용자 등록
		// 3.조회
		// 4.비교
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		UserDTO userWithLevel = users.get(4);
		userService.doSave(userWithLevel);
		assertEquals(1, mapper.getCount());

		UserDTO userWithOutLevel = users.get(0);
		userWithOutLevel.setGrade(null);
		userService.doSave(userWithOutLevel);
		assertEquals(2, mapper.getCount());

		// 3.
		UserDTO userGold = mapper.doSelectOne(userWithLevel);// GOLD
		UserDTO userBASIC = mapper.doSelectOne(userWithOutLevel);// BASIC

		assertEquals(userGold.getGrade(), Level.GOLD);
		assertEquals(userBASIC.getGrade(), Level.BASIC);

	}

	@Disabled
	@Test
	void fiveSave() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제
		// 2.5명 사용자 모두 입력

		log.debug("┌───────────────────────────┐");
		log.debug("│ *fiveSave()*              │");
		log.debug("└───────────────────────────┘");

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		for (UserDTO dto : users) {
			mapper.doSave(dto);
		}
		assertEquals(5, mapper.getCount());		
	}
	
	@Disabled
	@Test
	public void upgradeLevels() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1.전체삭제
		// 2.5명 사용자 모두 입력
		// 3.등업
		//

		// 4.데이터로 비교
		log.debug("┌───────────────────────────┐");
		log.debug("│ *upgradeLevels()*         │");
		log.debug("└───────────────────────────┘");

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		for (UserDTO dto : users) {
			mapper.doSave(dto);
		}
		assertEquals(5, mapper.getCount());

		// 3.
		userService.upgradeLevels();
		
//		userService.doSelectOne(users.get(0));
//
//		// 4.데이터로 비교
//		// BASIC 그대로
//		// BASIC -> SILVER
//		// SILVER 그대로
//		// SILVER -> GOLD
//		// GOLD
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true); // 등업
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true); // 등업
		checkLevel(users.get(4), false);

	}

	/**
	 * 등업 판단
	 * 
	 * @param user
	 * @param upgraded
	 * @throws SQLException
	 */
	private void checkLevel(UserDTO user, boolean upgraded) throws SQLException {
		UserDTO upgradeUser = mapper.doSelectOne(user);

		// 등업
		if (true == upgraded) {
			assertEquals(upgradeUser.getGrade(), user.getGrade().getNextLevel());

		} // No 등업
		else {
			assertEquals(upgradeUser.getGrade(), user.getGrade());
		}

	}

	@Disabled
	@Test
	void encrypt() throws Exception {
		String encrypt=aesUtil.encrypt("이상무");
		log.debug("***encrypt:"+encrypt);
		String decrypted= aesUtil.decrypt(encrypt);
		log.debug("***decrypted:"+decrypted);
		
		log.debug("***getSecretKey:"+aesUtil.getSecretKey());
		
	}
	
	@Disabled
	@Test
	void beans() {
		assertNotNull(aesUtil);
		assertNotNull(mapper);
		assertNotNull(context);
		assertNotNull(userService);
		assertNotNull(dataSource);
		assertNotNull(transactionManager);
		assertNotNull(mailSender);
		
		
		log.debug("context:" + context);
		log.debug("userService:" + userService);
		log.debug("mapper:" + mapper);
		log.debug("dataSource:" + dataSource);
		log.debug("transactionManager:" + transactionManager);
		log.debug("mailSender:" + mailSender);
		log.debug("aesUtil:" + aesUtil);
		log.debug("aesUtil:" + aesUtil.getSecretKey());
	}

}
