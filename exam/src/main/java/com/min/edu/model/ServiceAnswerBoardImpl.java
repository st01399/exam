package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.vo.AnswerBoardDto;

@Service
public class ServiceAnswerBoardImpl implements IServiceAnswerBoard{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDaoAnswerBoard dao;

	@Override
	public List<AnswerBoardDto> selectDynamic(Map<String, Object> map) {
		logger.info("selectDynamic");
		return dao.selectDynamic(map);
	}

	@Transactional
	@Override
	public boolean reply(AnswerBoardDto dto) {
		logger.info("reply");
		boolean isc1 = dao.replyUpdate(dto);
		boolean isc2 = dao.replyInsert(dto);
		return (isc1 || isc2) ? true : false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		logger.info("modifyBoard");
		return dao.modifyBoard(map);
	}

	@Override
	public boolean insertBoard(AnswerBoardDto dto) {
		logger.info("insertBoard");
		return dao.insertBoard(dto);
	}

	@Override
	public boolean multiDelete(String seq) {
		logger.info("multiDelete");
		return dao.multiDelete(seq);
	}

	@Override
	public boolean multiDelete2(Map<String, Object> map) {
		logger.info("multiDelete2");
		return dao.multiDelete2(map);
	}
	
	

}
