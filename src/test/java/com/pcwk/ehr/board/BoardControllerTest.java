package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.board.controller.BoardController;
import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.BoardMapper;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class BoardControllerTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	@Autowired
	BoardMapper  mapper;
	
	BoardDTO dto01;
	SearchDTO search;
	

	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setValidator(new LocalValidatorFactoryBean())
//                .build();
		dto01 = new BoardDTO(0, "제목1", "10", "내용1", 0, "사용안함", "Admin", "사용안함", "Admin");
		search = new SearchDTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Disabled
	@Test
	void doSaveException() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");
		
		//1.전체삭제
		//2.등록
		//3.상태값 비교
		
		//1.
		mapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/board/doSave.do")
		     .param("title", dto01.getTitle())
		     .param("div", dto01.getDiv())
		     .param("contents", dto01.getContents())
		     .param("regId", dto01.getRegId())
		     ;
		     
		//2.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			//.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("application/json"));
					
		//2.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);		  
		//2.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(0, resultMessage.getMessageId());
		assertEquals("서버 내부 오류가 발생했습니다.", resultMessage.getMessage());
		
	}
	@Disabled
	@Test
	void doSaveValid() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");
		
		//1.전체삭제
		//2.등록
		//3.상태값 비교
		
		//1.
		mapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/board/doSave.do")
		     .param("title", "")
		     .param("div", dto01.getDiv())
		     .param("contents", dto01.getContents())
		     .param("regId", dto01.getRegId())
		     ;
		     
		//2.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			//.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
					
		//2.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);		  
		//2.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(30, resultMessage.getMessageId());
		assertEquals("제목은 필수 입력 항목 입니다.", resultMessage.getMessage());
		
		
	}
	
	@Test
	void doRetrieve()  throws Exception{
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");		
		
		//1.전체삭제
		//2.다건등록(saveAll)
		//3.목록 조회
		//4.비교
		
		//1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());		
		
		//2.
		int count = mapper.saveAll();
		assertEquals(502, mapper.getCount());
		
		
		//3.
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.get("/board/doRetrieve.do")	
				  .param("pageNo", "1")
				  .param("pageSize", "10")
				  .param("div", "10")
				  .param("searchDiv", "10")
				  .param("searchWord", "제목3");
				  
		//3.1
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());				  
				  
		MvcResult mvcResult =resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		int totalCnt = (int) model.get("totalCnt");
		log.debug("totalCnt:{}",totalCnt);
		
		
		List<BoardDTO> list = (List<BoardDTO>) model.get("list");
		for(BoardDTO vo  : list) {
			log.debug(vo);
		}
		
		assertEquals(10, list.size());
	}
	
	
	
	
	@Disabled
	@Test
	void doSelectOne() throws Exception{
		//1.전체삭제
		//2.등록
		//3.단건조회
		
		//1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		

		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		log.debug("after:{}",dto01);	
		
		//3.
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.get("/board/doSelectOne.do")
		     .param("seq", String.valueOf(dto01.getSeq()))
		     .param("regId", String.valueOf(dto01.getRegId()+"UP"));		
		//3.1
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		//3.2		
		MvcResult mvcResult =resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		BoardDTO outVO = (BoardDTO) model.get("vo");
		log.debug("outVO:{}",outVO);
		
		//조회count 증가
		dto01.setReadCnt(dto01.getReadCnt()+1);
		
		isSameBoard(outVO,dto01);
		
		//3.3
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);	
		
		assertEquals("board/board_mod", viewName);
		
	}
	
	private void isSameBoard(BoardDTO  outVO,BoardDTO dto01 ) {
		assertEquals(outVO.getSeq(), dto01.getSeq());
		assertEquals(outVO.getTitle(), dto01.getTitle());
		assertEquals(outVO.getDiv(), dto01.getDiv());
		assertEquals(outVO.getContents(), dto01.getContents());
		assertEquals(outVO.getReadCnt(), dto01.getReadCnt());
		
		assertEquals(outVO.getRegId(), dto01.getRegId());
		assertEquals(outVO.getModId(), dto01.getModId());
	}
	
	
	
	@Disabled
	@Test
	void doUpdate() throws Exception{
		//1.전체삭제
		//2.등록
		//3.한건조회
		//4.수정
		
		//1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		

		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		log.debug("after:{}",dto01);
		
		//3.
		BoardDTO outVO = mapper.doSelectOne(dto01);
		assertNotNull(outVO);
		log.debug("outVO:{}",outVO);
		
		String upString = "_U";
		int    upInt    = 99;
		//4.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/board/doUpdate.do")
		     .param("title", outVO.getTitle()+upString)
		     .param("div", outVO.getDiv())
		     .param("contents", outVO.getContents()+upString)
		     .param("modId", outVO.getModId()+upString)
		     .param("seq", String.valueOf(outVO.getSeq()));
		     ;
		//4.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
					
		//4.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);		
		//4.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("수정 되었습니다.", resultMessage.getMessage());		
		
	}
	
	@Disabled
	@Test
	void doDelete() throws Exception{
		log.debug("┌───────────────────────────┐");
		log.debug("│ doDelete()                │");
		log.debug("└───────────────────────────┘");		
		
		//1.전체삭제
		//2.등록
		//3.삭제
		
		//1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		log.debug("after:{}",dto01);
		
		//3.
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/board/doDelete.do")
		     .param("seq", String.valueOf(dto01.getSeq()));		
		
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(
				MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
						
		//2.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);	
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("삭제 되었습니다.", resultMessage.getMessage());		
	}
	
	@Disabled
	@Test
	void doSave() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");
		
		//1.전체삭제
		//2.등록
		//3.상태값 비교
		
		//1.
		mapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/board/doSave.do")
		     .param("title", dto01.getTitle())
		     .param("div", dto01.getDiv())
		     .param("contents", dto01.getContents())
		     .param("regId", dto01.getRegId())
		     ;
		     
		//2.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
					
		//2.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);		  
		//2.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("제목1 등록 되었습니다.", resultMessage.getMessage());
		
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
