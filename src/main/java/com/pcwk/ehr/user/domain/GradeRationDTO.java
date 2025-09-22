package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class GradeRationDTO extends DTO {

	private String grade; // 등급
	private int memberCount;// 인원수
	private int year; // 년도
	/**
	 * 
	 */
	public GradeRationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param grade
	 * @param memberCount
	 * @param year
	 */
	public GradeRationDTO(String grade, int memberCount, int year) {
		super();
		this.grade = grade;
		this.memberCount = memberCount;
		this.year = year;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the memberCount
	 */
	public int getMemberCount() {
		return memberCount;
	}
	/**
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "GradeRationDTO [grade=" + grade + ", memberCount=" + memberCount + ", year=" + year + "]";
	}
	
	
	
}
