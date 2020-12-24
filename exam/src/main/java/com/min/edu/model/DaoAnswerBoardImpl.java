package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.AnswerBoardDto;

@Repository
public class DaoAnswerBoardImpl implements IDaoAnswerBoard {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS ="com.min.edu.model.IServiceAnswerBoard.";
	
	@Override
	public List<AnswerBoardDto> selectDynamic(Map<String, Object> map) {
		return session.selectList(NS+"selectDynamic",map);
	}

	@Override
	public boolean replyInsert(AnswerBoardDto dto) {
		int n = session.insert(NS+"replyInsert",dto);
		return n>0 ? true : false;
	}

	@Override
	public boolean replyUpdate(AnswerBoardDto dto) {
		int n = session.update(NS+"replyUpdate",dto);
		return n>0 ? true : false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		int n = session.update(NS+"modifyBoard",map);
		return n>0 ? true : false;
	}

	@Override
	public boolean insertBoard(AnswerBoardDto dto) {
		int n = session.insert(NS+"insertBoard",dto);
		return n>0 ? true : false;
	}

	@Override
	public boolean multiDelete(String seq) {
		int n = session.delete(NS+"multiDelete",seq);
		return n > 0? true : false;
	}

	@Override
	public boolean multiDelete2(Map<String, Object> map) {
		int n = session.delete(NS+"multiDelete2",map);
		return n > 0? true : false;
	}

	
	
	
	

}
