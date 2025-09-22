package com.pcwk.ehr.markdown.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchDTO;

@Service
public class MarkdownServiceImpl implements MarkdownService {
	Logger log = LogManager.getLogger(getClass());

	public MarkdownServiceImpl() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ MarkdownServiceImpl()                                   │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Override
	public String convertMakrdownToHtml(String makrdown) {
		String htmlString = "";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *convertMakrdownToHtml()* │");
		log.debug("└───────────────────────────┘");

		log.debug("1. param:" + makrdown);

		Parser parser = Parser.builder().build();

		Node document = parser.parse(makrdown);

		HtmlRenderer render = HtmlRenderer.builder().build();

		htmlString = render.render(document);

		log.debug("2. htmlString:" + htmlString);

		return htmlString;
	}

	@Override
	public List<DTO> doRetrieve(SearchDTO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doDelete(DTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(DTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO doSelectOne(DTO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(DTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

}
