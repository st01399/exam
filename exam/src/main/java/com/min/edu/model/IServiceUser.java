package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.UserDto;

public interface IServiceUser {

	public List<UserDto> memberList();
	public boolean signUpMember(UserDto dto);
	public Integer idDuplicateCheck(String id);
	public UserDto loginMember(Map<String, Object> map);

}
