package com.hk.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHKService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	public IHKService hkServiceImp;
									  //method를 안쓰면 get이든 post든 상관없이 쓸수 있음
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
									//Model:requset.setAttribute("list",list)
									//		dispatch.foward(url)
									//객체를 전달할때 사용: addAttribute()
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/boardlist.do",method = RequestMethod.GET)
	public String boardList(Model model) {
		List<HkDto> lists=hkServiceImp.getAllBoard();
		model.addAttribute("list",lists);
		return "boardlist";//viewResolver에 의해/WEB-INF/views/boardlist.jsp 찾아줌
	}
	
	@RequestMapping(value = "/boardwrite.do",method = RequestMethod.GET)
	public String boardWritwForm(Model model) {
		return "boardwrite";//viewResolver에 의해/WEB-INF/views/boardlist.jsp 찾아줌
	}
	
	@RequestMapping(value = "/insertboard.do",method = RequestMethod.POST)
	public String insetboard(Model model,HkDto dto) {//전달되는 파라미터를 이름만 일치하면 받아짐
		boolean isS=hkServiceImp.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글쓰기 실패");
			return "error";			
		}
	}
	
	@RequestMapping(value = "/boarddetail.do",method = RequestMethod.GET)
	public String boardDetail(Model model, String seq) {//전달되는 파라미터를 이름만 일치하면 받아짐
		int sseq=Integer.parseInt(seq);
		HkDto dto=hkServiceImp.getBoard(sseq);		
		model.addAttribute("dto", dto);
		return "boarddetail";			
		
	}

	@RequestMapping(value = "/updateform.do",method = RequestMethod.GET)
	public String updateForm(Model model, String seq) {//전달되는 파라미터를 이름만 일치하면 받아짐
		int sseq=Integer.parseInt(seq);
		System.out.println(sseq);
		HkDto dto=hkServiceImp.getBoard(sseq);		
		model.addAttribute("dto", dto);
		return "boardupdate";				
	}

	@RequestMapping(value = "/boardupdate.do",method = RequestMethod.POST)
	public String boardUpdate(Model model, HkDto dto) {//전달되는 파라미터를 이름만 일치하면 받아짐
		boolean isS=hkServiceImp.updateBoard(dto);
		if(isS) {
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg", "수정실패");
			return "error";				
		}
	}
	
	@RequestMapping(value = "/delboard.do",method = RequestMethod.GET)
	public String delBoard(Model model, HkDto dto) {//전달되는 파라미터를 이름만 일치하면 받아짐
		boolean isS=hkServiceImp.deleteBoard(dto.getSeq());
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "삭제실패");
			return "error";				
		}
	}
}
