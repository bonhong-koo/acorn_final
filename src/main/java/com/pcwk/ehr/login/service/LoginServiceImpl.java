package com.pcwk.ehr.login.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.UserDTO;

@Service
public class LoginServiceImpl implements LoginService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserMapper mapper;

	public LoginServiceImpl() {

	}

	@Override
	public int idPassCheck(UserDTO param) {
		// flag->10 :id불일치
		// flag->20 :비번 불일치
		// flag->30 :id/비번 일치
		int flag = 30;

		// id불일치
		int count = mapper.idCheck(param);

		if (1 != count) {
			flag = 10;
			return flag;
		}

		// 비번 비교
		// 1. 회원 단건 조회
		// 2. 비번 : matches
		UserDTO outVO = mapper.doSelectOne(param);
		String rawPassword = param.getPassword();
		log.debug("rawPassword: {}", outVO.getPassword());
		log.debug("encodedPassowrd: {}", rawPassword);
		boolean isMatch = passwordEncoder.matches(rawPassword, outVO.getPassword());
		if (isMatch == false) {
			flag = 20;
			return flag;
		}

		log.trace("flag:{}", flag);

		return flag;
	}

	@Override
	public UserDTO doSelectOne(UserDTO param) {
		return mapper.doSelectOne(param);
	}

}
