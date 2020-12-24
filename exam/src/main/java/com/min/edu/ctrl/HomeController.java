package com.min.edu.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.valves.JsonErrorReportValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.min.edu.model.IServiceAnswerBoard;
import com.min.edu.model.IServiceUser;
import com.min.edu.vo.AnswerBoardDto;
import com.min.edu.vo.UserDto;


@Controller
public class HomeController {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IServiceAnswerBoard dao;
	
	@Autowired
	private IServiceUser uDao; 
	
	
	// 게시판 전체 조회
	@RequestMapping(value = "/boardList.do",method = RequestMethod.GET)
	public String boardList(Model model) {
		model.addAttribute("lists",dao.selectDynamic(null));
		return "boardList";
	}
	
	// 게시판 상세 조회
	@RequestMapping(value = "/detailBoard.do",method = RequestMethod.GET)
	public String detailBoard(Model model,String seq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq",seq);
		model.addAttribute("dto",dao.selectDynamic(map).get(0));
		return "detailBoard";
	}
	
	// 게시판 단일 / 다중삭제
	@RequestMapping(value = "/delete.do",method = RequestMethod.GET)
	public String delete(String seq) {
		dao.multiDelete(seq);
		return "redirect:/boardList.do";
	}
	
	@RequestMapping(value = "/multiDelete.do",method = RequestMethod.POST)
	public String multiDelete(@RequestParam("chks") String[] seq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seqs",seq);
		dao.multiDelete2(map);
		return "redirect:/boardList.do";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/modifyMove.do",method = RequestMethod.GET)
	public String modifyMove(Model model,@RequestParam Map<String, Object> map,HttpSession session) {
		log.info("modify : {}",map);
		List<AnswerBoardDto> dto = dao.selectDynamic(map);
		log.info("modify : {}",dto);
		model.addAttribute("user",session.getAttribute("infomation"));
		model.addAttribute("dto",dto.get(0));
		return "modifyForm";
	}
	
	@RequestMapping(value = "/modify.do",method = RequestMethod.POST)
	public String modify(@RequestParam Map<String,Object> map,HttpSession session) {
		log.info("modify : {}",map);
		map.put("id",((UserDto)session.getAttribute("infomation")).getId());
		dao.modifyBoard(map);
		return "redirect:/detailBoard.do";
	}
	
	
	
	// 게시판 입력
	@RequestMapping(value = "/writeMove.do",method = RequestMethod.GET)
	public String writeMove(HttpSession session,Model model) {
		model.addAttribute("user",session.getAttribute("infomation"));
		return "writeForm";
	}
	
	@RequestMapping(value = "/write.do",method = RequestMethod.POST)
	public String write(@RequestParam Map<String, Object> map,Model model,HttpSession session) {
		log.info("bDto : {}",map);
		AnswerBoardDto dto = new AnswerBoardDto(0, ((UserDto)session.getAttribute("infomation")).getId() ,(String) map.get("title"),(String) map.get("content"));
		dao.insertBoard(dto);
		return "redirect:/detailBoard.do?seq="+dto.getSeq();
	}
	
	// 답글 입력
	@RequestMapping(value = "/replyMove.do",method = RequestMethod.GET)
	public String replyMove(String seq,Model model,HttpSession session) {
		model.addAttribute("seq",seq);
		model.addAttribute("user",session.getAttribute("infomation"));
		return "writeForm";
	}
	
	@RequestMapping(value = "/reply.do",method = RequestMethod.POST)
	public String reply(AnswerBoardDto dto,HttpSession session) {
		dto.setId(((UserDto)session.getAttribute("infomation")).getId());
		dao.reply(dto);
		return "redirect:/boardList.do";
	}
	
	//-----------------------------------------------
	
	// 회원가입
	@RequestMapping(value = "/registerMove.do",method = RequestMethod.GET)
	public String registerMove() {
		return "register";
	}
	
	@RequestMapping(value = "/register.do",method = RequestMethod.POST)
	public String register(UserDto dto) {
		uDao.signUpMember(dto);
		return "redirect:/login.do";
	}
	
	
	// 아이디 중복 체크
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> idCheck(String id) {
		int n = uDao.idDuplicateCheck(id);
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(n);
		if(n>0) {
			map.put("isc", false);
		} else {
			map.put("isc",true);
		}
		return map;
	}
	
	// 로그인
	@RequestMapping(value = "/login.do",method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/loginSuccess.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpSession session,@RequestParam Map<String, Object> map) {
		UserDto dto = uDao.loginMember(map);
		Map<String, Object> nMap = new HashMap<String, Object>();
		nMap.put("isc", false);
		if(dto != null) {
			session.setAttribute("infomation", dto);
			nMap.put("isc", true);
		}
		return nMap;
	}
	
	
	// 로그아웃
	@RequestMapping(value = "/logout.do",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("infomation") != null) {
			session.invalidate();
		}
		return "redirect:/login.do";
	}
	
	
	@RequestMapping(value = "/error.do",method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
}
