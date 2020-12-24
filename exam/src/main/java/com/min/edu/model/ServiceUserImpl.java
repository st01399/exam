package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.min.edu.vo.UserDto;

@Service
public class ServiceUserImpl implements IServiceUser {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IDaoUser iDao;
	
	@Override
	public List<UserDto> memberList() {
		logger.info("memberList");
		return iDao.memberList();
	}

	@Override
	public boolean signUpMember(UserDto dto) {
		logger.info("signUpMember");
		dto.setPw(passwordEncoder.encode(dto.getPw()));
		return iDao.signUpMember(dto);
	}

	@Override
	public Integer idDuplicateCheck(String id) {
		logger.info("idDuplicateCheck");
		return iDao.idDuplicateCheck(id);
	}

	@Override
	public UserDto loginMember(Map<String, Object> map) {
		logger.info("loginMember");
		
		UserDto user = null;
		String encoderPw = iDao.selStringPw((String)map.get("id"));
		if(passwordEncoder.matches((String)map.get("pw"), encoderPw)) {
			user = iDao.enLogin(map);
		}
		
		return user;
	}


}
