package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.UserDto;

@Repository
public class DaoUserImpl implements IDaoUser {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.min.edu.model.IServiceUser.";
	
	@Override
	public List<UserDto> memberList() {
		return session.selectList(NS+"memberList");
	}

	@Override
	public boolean signUpMember(UserDto dto) {
		int n = session.insert(NS+"signUpMember",dto);
		return n>0 ? true : false;
	}

	@Override
	public Integer idDuplicateCheck(String id) {
		return session.selectOne(NS+"idDuplicateCheck",id);
	}

	@Override
	public UserDto loginMember(Map<String, Object> map) {
		return session.selectOne(NS+"loginMember",map);
	}

	@Override
	public String selStringPw(String id) {
		return session.selectOne(NS+"selStringPw",id);
	}

	@Override
	public UserDto enLogin(Map<String, Object> map) {
		return session.selectOne(NS+"enLogin",map);
	}

}
