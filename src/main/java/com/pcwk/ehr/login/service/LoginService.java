package com.pcwk.ehr.login.service;

import com.pcwk.ehr.user.domain.UserDTO;

public interface LoginService {

	
	/**
	 * id,password 체크
	 */
	
	int idPassCheck(UserDTO param);
	
	/**
	 * 단건조회
	 * @param param
	 * @return
	 */
	UserDTO doSelectOne(UserDTO param);
	
}
