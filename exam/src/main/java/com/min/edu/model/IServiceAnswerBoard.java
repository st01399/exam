package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.AnswerBoardDto;

public interface IServiceAnswerBoard {

	public List<AnswerBoardDto> selectDynamic(Map<String, Object> map);
	public boolean reply(AnswerBoardDto dto);
	public boolean modifyBoard(Map<String,Object> map);
	public boolean insertBoard(AnswerBoardDto dto);
	public boolean multiDelete(String seq);
	public boolean multiDelete2(Map<String,Object> map);
	
}
