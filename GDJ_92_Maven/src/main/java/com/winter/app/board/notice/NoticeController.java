package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;



@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeDAO noticeDao;
//	@GetMapping("add")
//	public void insert()throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDao.insert(noticeVO);
//		
//	}
	@GetMapping("list")
	public String list(Model model)throws Exception{
		//Model- > requset랑 life cycle이 유사하고 스프링에서 jsp까지 데이터를 전달할 때 사용
		List<BoardVO> list= noticeService.list();
		model.addAttribute("list", list);
		return "board/list";
		
	}
	
	@GetMapping("detail")
	public void detail(NoticeVO noticeVO,Model model)throws Exception{
		
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("detail",boardVO);
		
	}
	@GetMapping("add")
	public String add() {
		return "/board/add";
	}
	@PostMapping("add")
	public String add(NoticeVO noticeVO,Model model)throws Exception {
		
		int result =noticeService.insert(noticeVO);
		return "redirect:/board/list";
	}
	
	@GetMapping("update")
	public String update(BoardVO noticeVO ,Model model)throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		
		return"board/add";
	}
	@PostMapping("update")
	public String update(NoticeVO noticeVO,Model model)throws Exception {
		int result = noticeService.update(noticeVO);
		String msg = "수정 실패";
		
		if(result>0) {
			msg="수정 성공";
		}
		
		String url="./detail?boardNum="+noticeVO.getBoardNum();
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "commons/result";   //"redirect:/notice/detail?boardNum="+noticeVO.getBoardNum();
		
	}
	@PostMapping("delete")
	public String delete(NoticeVO noticeVO,Model model)throws Exception{
		int result =noticeService.delete(noticeVO);
		String msg = "삭제 실패";
		String url ="./list";
		if(result>0) {
			msg="삭제 성공";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "commons/result";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
