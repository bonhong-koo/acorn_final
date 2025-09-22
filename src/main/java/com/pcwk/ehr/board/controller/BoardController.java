package com.pcwk.ehr.board.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.PcwkString;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.markdown.service.MarkdownService;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger log = LogManager.getLogger(getClass());

	
	@Autowired
	MarkdownService markdownService;
	
	@Autowired
	BoardService boardService;
	
	/**
	 * 
	 */
	public BoardController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ BoardController()                                       │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	// 화면목록: /board/board_list
	// 등록 : /board/board_reg
	// 수정 : /board/board_mod
	
	//등록화면조회	/board/doSaveView.do	doSaveView()	동기	GET	
	@GetMapping("/doSaveView.do")
	public String doSaveView(@RequestParam(name = "div", defaultValue = "10")String div, Model model) {
		String viewNString = "board/board_reg";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSaveView()*            │");
		log.debug("└───────────────────────────┘");	
		log.debug("div: {}",div);
		model.addAttribute("board_div", div);
		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	}
	
	
	
	
	//목록	/board/doRetrieve.do	doRetrieve(SearchDTO search)	동기	GET	Model
	@GetMapping(value = "/doRetrieve.do")
	public String doRetrieve(SearchDTO param,Model model) {
		String viewName = "board/board_list";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");	
		
		
		
		int pageNo = PcwkString.nvlZero(param.getPageNo(), 1);
		int pageSize = PcwkString.nvlZero(param.getPageSize(), 10);
		
		//게시구분: 공지사항(10)
		String div   = PcwkString.nvlString(param.getDiv(), "10");
		//검색구분
		String searchDiv = PcwkString.nullToEmpty(param.getSearchDiv());
		//검색어
		String searchWord = PcwkString.nullToEmpty(param.getSearchWord());
		
		log.debug("pageNo:{}",pageNo);
		log.debug("pageSize:{}",pageSize);
		log.debug("div:{}",div);
		log.debug("searchDiv:{}",searchDiv);
		log.debug("searchWord:{}",searchWord);
		
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setDiv(div);
		param.setSearchDiv(searchDiv);
		param.setSearchWord(searchWord);
		
		log.debug("***param:{}",param);
		List<BoardDTO> list= boardService.doRetrieve(param);
		
		model.addAttribute("list", list);
		
		//총글수
		int totalCnt = 0;
		
		if(null != list && list.size()>0) {
			BoardDTO totalVO =  list.get(0);
			totalCnt = totalVO.getTotalCnt();
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("divValue", div);
		//Form Submit: 파라메터 값 유지
		model.addAttribute("search", param);
		
		return viewName;
	}
	
	
	//단건조회	/board/doSelectOne.do	doSelectOne(BoardDTO param)	동기	GET	Model
	@GetMapping(value = "/doSelectOne.do" )
	public String doSelectOne(BoardDTO param,Model model) {
		String viewName = "board/board_mod";
		//   /WEB-INF/views/+board/board_mod+.jsp
		log.debug("1. param: {}",param);
		
		//게시구분: 공지사항(10)
		String div   = PcwkString.nvlString(param.getDiv(), "10");
		log.debug("2. div: {}",div);
		
		BoardDTO  outVO = boardService.doSelectOne(param);
		String html = markdownService.convertMakrdownToHtml(outVO.getContents());
		log.debug("3. outVO: {}",outVO);
		
		model.addAttribute("vo", outVO);
		model.addAttribute("divValue", div);
		model.addAttribute("contentsTextAreaHtml", html);
		log.debug("4. html: {}",html);
		
		
		
		
		
		
		return viewName;
	}
	//수정	/board/doUpdate.do	doUpdate(BoardDTO param)	비동기	POST	JSON
	@PostMapping(value = "/doUpdate.do",produces ="text/plain;charset=UTF-8" )
	@ResponseBody
	public String doUpdate(BoardDTO param, HttpServletRequest req) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*              │");
		log.debug("└───────────────────────────┘");		
		log.debug("1. param: {}",param);

		int flag = boardService.doUpdate(param);
		String message = "";
		if(1==flag) {
			message = "수정 되었습니다.";
		}else {
			message = "수정 실패.";
		}

		return new Gson().toJson(new MessageDTO(flag, message));
	}
	
	
	
	//삭제	/board/doDelete.do	doDelete(BoardDTO param)	비동기	POST	JSON
	@PostMapping(value = "/doDelete.do",produces ="text/plain;charset=UTF-8" )
	@ResponseBody
	public String doDelete(BoardDTO param, HttpServletRequest req) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doDelete()*              │");
		log.debug("└───────────────────────────┘");
		
		log.debug("1. param: {}",param);
		String jsonString = "";
		int flag = boardService.doDelete(param);
		
		String message = "";
		if(1==flag) {
			message = "삭제 되었습니다.";
		}else {
			message = "삭제 실패!";
		}
		
		jsonString = new Gson().toJson(new MessageDTO(flag, message));
		log.debug("2. jsonString:{}",jsonString);
		return jsonString;
	}
	
	@PostMapping(value="/doSave.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(BoardDTO param) {
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");
		String jsonString = "";
		log.debug("1. param:{}",param);
		
		int flag = boardService.doSave(param);
		String message = "";
		
		if(1 == flag) {
			message = param.getTitle()+" 등록 되었습니다.";
		}else {
			message = param.getTitle()+" 등록 실패 했습니다.";
		}
		
		//모든 사용자에게 메시지 전송 

		MessageDTO   messageDTO = new MessageDTO(flag, message);
		jsonString = new Gson().toJson(messageDTO);
		log.debug("2. jsonString:{}",jsonString);
		return jsonString;
	}


}
