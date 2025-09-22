package com.pcwk.ehr.board.domain;

import com.pcwk.ehr.cmn.DTO;

public class BoardDTO extends DTO {

	private int seq;// 순번

	private String title;// 제목

	private String div;// 게시구분
	private String contents;// 내용
	private int readCnt;// 조회수
	private String regDt;// 등록일
	private String regId;// 등록자
	private String modDt;// 수정일
	private String modId;// 수정자

	public BoardDTO() {
	}

	/**
	 * @param seq
	 * @param title
	 * @param div
	 * @param contents
	 * @param readCnt
	 * @param regDt
	 * @param regId
	 * @param modDt
	 * @param modId
	 */
	public BoardDTO(int seq, String title, String div, String contents, int readCnt, String regDt, String regId,
			String modDt, String modId) {
		super();
		this.seq = seq;
		this.title = title;
		this.div = div;
		this.contents = contents;
		this.readCnt = readCnt;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the div
	 */
	public String getDiv() {
		return div;
	}

	/**
	 * @param div the div to set
	 */
	public void setDiv(String div) {
		this.div = div;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the readCnt
	 */
	public int getReadCnt() {
		return readCnt;
	}

	/**
	 * @param readCnt the readCnt to set
	 */
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the modDt
	 */
	public String getModDt() {
		return modDt;
	}

	/**
	 * @param modDt the modDt to set
	 */
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}

	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", div=" + div + ", contents=" + contents + ", readCnt="
				+ readCnt + ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", modId=" + modId
				+ ", toString()=" + super.toString() + "]";
	}

}
