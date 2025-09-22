package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface BoardMapper extends WorkDiv<BoardDTO> {
	
	/**
	 * 조회 count증가(단, 본인글 이외 글만)
	 * @param param
	 * @return 반경 건수 
	 */
	int updateReadCnt(BoardDTO param);
	
	/**
	 * 다건 등록
	 * @return 등록건수
	 */
	int saveAll();
	
    /**
     * sequence조회
     * @return
     */
	int getBoardSeq();

	/**
	 * 전체삭제
	 */
	int deleteAll();
	
	/**
	 * 전체 건수
	 */
	int getCount();
	
}
