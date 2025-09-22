package com.pcwk.ehr.markdown.service;

import com.pcwk.ehr.board.domain.BoardDTO;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public interface MarkdownService  extends WorkDiv<DTO> {

	/**
	 * 마크다운을 To Html로 변환
	 * @param makrdown
	 * @return String(HTML)
	 */
	public String convertMakrdownToHtml(String makrdown);
}
