package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeDAO noticeDao;
	@GetMapping("add")
	public void insert()throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title");
		noticeVO.setBoardContents("contents");
		noticeVO.setBoardWriter("writer");
		int result = noticeDao.insert(noticeVO);
		
	}
	@GetMapping("list")
	public String list(Model model)throws Exception{
		//Model- > requset랑 life cycle이 유사하고 스프링에서 jsp까지 데이터를 전달할 때 사용
		List<BoardVO> list= noticeService.list();
		model.addAttribute("list", list);
		return "notice/list";
		
	}
	
	@GetMapping("detail")
	public void detail(NoticeVO noticeVO,Model model)throws Exception{
		
		NoticeVO noticeVo = (NoticeVO)noticeService.detail(noticeVO);
		model.addAttribute("detail",noticeVo);
	}
}
