package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.UserDto;

public interface IDaoUser {

	public List<UserDto> memberList();
	public boolean signUpMember(UserDto dto);
	public Integer idDuplicateCheck(String id);
	/**
	 * 시큐리티 안쓸때 필요
	 * @param map
	 * @return
	 */
	public UserDto loginMember(Map<String, Object> map);
	public String selStringPw(String id);
	public UserDto enLogin(Map<String, Object> map);

}
