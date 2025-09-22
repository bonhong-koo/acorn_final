package com.pcwk.ehr.file.domain;

import com.pcwk.ehr.cmn.DTO;

public class FileVO extends DTO {
	
	private String orgFileName;//원본 파일명
	private String saveFileName;//저장 파일명
	private String savePath;//저장 경로
	private String fileType;//image/none image
	private String fileUrl;

	private long  fileSize;//파일 크기
	private String ext;//확장자

	public FileVO() {

	}
	
	
	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}


	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}	
	/**
	 * @return the orgFileName
	 */
	public String getOrgFileName() {
		return orgFileName;
	}
	/**
	 * @param orgFileName the orgFileName to set
	 */
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	/**
	 * @return the saveFileName
	 */
	public String getSaveFileName() {
		return saveFileName;
	}
	/**
	 * @param saveFileName the saveFileName to set
	 */
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}
	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}


	@Override
	public String toString() {
		return "FileVO [orgFileName=" + orgFileName + ", saveFileName=" + saveFileName + ", savePath=" + savePath
				+ ", fileType=" + fileType + ", fileUrl=" + fileUrl + ", fileSize=" + fileSize + ", ext=" + ext + "]";
	}

	
	

	
	
	
}
