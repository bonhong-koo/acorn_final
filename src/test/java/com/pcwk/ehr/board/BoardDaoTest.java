package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.BoardMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class BoardDaoTest {
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	BoardMapper mapper;
	
	BoardDTO dto01;
	BoardDTO dto02;
	
	SearchDTO search;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		//시퀀스 조회
		//10:공지사항, 20: 자유게시판
//		int seq1 = mapper.getBoardSeq();
//		int seq2 = mapper.getBoardSeq();
//		//int seq = -99;
//		log.debug("seq1:{}",seq1);
//		log.debug("seq2:{}",seq2);
		
		dto01 = new BoardDTO(0, "제목1", "10", "내용1", 0, "사용안함", "Admin", "사용안함", "Admin");
		dto02 = new BoardDTO(0, "제목1", "10", "내용1", 0, "사용안함", "Admin", "사용안함", "Admin");
		
		search = new SearchDTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
	}
	
	@Test
	void updateReadCnt() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *updateReadCnt()*         │");
		log.debug("└───────────────────────────┘");		
		
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 한건 등록
		//3. 조회건수 증가
		//4. 조회 비교
		
		//1.
		mapper.deleteAll();
		
		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		log.debug("after:{}",dto01);
		assertEquals(1, flag);		
		
		// 2.1
		int count = mapper.getCount();
		log.debug("count:{}",count);
		assertEquals(1, count);	
		
		//글 등록자를 : Admin -> james
		dto01.setRegId("james");
		//3.
		flag = mapper.updateReadCnt(dto01);
		assertEquals(1, flag);	
		
		//4.
		BoardDTO outVO=mapper.doSelectOne(dto01);
		log.debug("outVO:{}",outVO);
		assertEquals(1, outVO.getReadCnt());
		log.debug("outVO.getReadCnt():{}",outVO.getReadCnt());
	}
	
	@Disabled
	@Test
	void doRetrieve() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");		
		
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 다건등록
		//3. paging조회
		
		
		//1.
		mapper.deleteAll();
		
		//2.
		int count = mapper.saveAll();
		log.debug("count:{}",count);
		assertEquals(502, count);
		
		//paging
		search.setPageNo(1);
		search.setPageSize(10);
		search.setDiv("20");
		
		//search.setSearchDiv("40");
		//search.setSearchWord("제목1");
		
		//3.
		List<BoardDTO> list = mapper.doRetrieve(search);
		for(BoardDTO vo  :list) {
			log.debug(vo);
		}
		
		
	}
	
	
	@Disabled
	@Test
	void doUpdate() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*              │");
		log.debug("└───────────────────────────┘");		
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
		
		//1.
		mapper.deleteAll();
		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		log.debug("after:{}",dto01);
		log.debug("flag:{}",flag);
		assertEquals(1, flag);		
		
		// 2.1
		int count = mapper.getCount();
		log.debug("count:{}",count);
		assertEquals(1, count);		
		
		// 3. 
		BoardDTO  outVO = mapper.doSelectOne(dto01);
		assertNotNull(outVO);
		log.debug("outVO:{}",outVO);		
		
		isSaveBoard(outVO,dto01);
		//4. 
		String upString = "_U";
		int    upInt    = 999;
		
		outVO.setTitle(outVO.getTitle()+upString);
		outVO.setContents(outVO.getContents()+upString);
		outVO.setDiv(outVO.getDiv()+upInt);
		outVO.setModId(outVO.getModId()+upString);
		
		//5.
		flag = mapper.doUpdate(outVO);
		assertEquals(1, flag);
		
		//6. 수정 조회
		BoardDTO  upVO = mapper.doSelectOne(outVO);
		isSaveBoard(upVO, outVO);
		
	}
	
	

	@Disabled
	@Test
	void doDelete() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doDelete()*              │");
		log.debug("└───────────────────────────┘");			
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록건수 비교
		//3. 삭제
		//4. 등록건수 비교==0
		
		//1.
		mapper.deleteAll();
		
		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		log.debug("after:{}",dto01);
		log.debug("flag:{}",flag);
		assertEquals(1, flag);		
		
		// 2.1
		int count = mapper.getCount();
		log.debug("count:{}",count);
		assertEquals(1, count);
		
		// 3.
		flag = mapper.doDelete(dto01);
		assertEquals(1, flag);		
		
		//4
		count = mapper.getCount();
		log.debug("count:{}",count);
		assertEquals(0, count);
		
	}
	
	@Disabled
	@Test
	void addAndGet() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *addAndGet()*             │");
		log.debug("└───────────────────────────┘");				
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		
		// 2.1 전체건수 조회
		// 3. 단건조회
		// 4. 비교
		
		//1.
		mapper.deleteAll();
		
		//2.
		log.debug("before:{}",dto01);
		int flag = mapper.doSave(dto01);
		log.debug("after:{}",dto01);
		log.debug("flag:{}",flag);
		assertEquals(1, flag);
		
		// 2.1
		int count = mapper.getCount();
		log.debug("count:{}",count);
		assertEquals(1, count);		
		
		// 3. 
		BoardDTO  outVO = mapper.doSelectOne(dto01);
		assertNotNull(outVO);
		log.debug("outVO:{}",outVO);
		
		isSaveBoard(outVO,dto01);
	}
	
	private void isSaveBoard(BoardDTO  outVO,BoardDTO dto01 ) {
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
	void beans() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *beans()*                 │");
		log.debug("└───────────────────────────┘");		
		
		assertNotNull(context);
		assertNotNull(mapper);
		assertNotNull(dto01);
		
		assertNotEquals(0, dto01.getSeq());
		
		log.debug("context: {}",context);
		log.debug("mapper: {}",mapper);
		log.debug("dto01.getSeq(): {}",dto01.getSeq());
		
	}

	
	
	
	
	
	
	
	
}
