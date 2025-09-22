package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.user.domain.GradeRationDTO;
import com.pcwk.ehr.user.domain.UserDTO;

public interface UserService {

	// BASIC -> SILVER 로그인횟수
	int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	// SILVER -> GOLD 추천횟수
	int MIN_RECOMMEND_FOR_GOLD = 30;

	List<GradeRationDTO> yearGradeRation(GradeRationDTO param);
	
	List<UserDTO> doRetrieve(SearchDTO param) throws Exception;

	int doDelete(UserDTO param);

	int doUpdate(UserDTO param) throws Exception;

	UserDTO doSelectOne(UserDTO param) throws Exception;

	int doSave(UserDTO param) throws Exception;

	// 전체 회원을 대상으로 등업
	// ...
	void upgradeLevels() throws SQLException;

}