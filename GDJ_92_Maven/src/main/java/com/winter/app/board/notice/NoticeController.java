package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/notice/*")
@Slf4j
public class NoticeController {

    private final FileManager fileManager;
	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;

	
	
	@Autowired
	private NoticeDAO noticeDao;

    NoticeController(FileManager fileManager) {
        this.fileManager = fileManager;
    }
//	@GetMapping("add")
//	public void insert()throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDao.insert(noticeVO);
//		
//	}
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception{
		//Model- > requset랑 life cycle이 유사하고 스프링에서 jsp까지 데이터를 전달할 때 사용
		
		
		List<BoardVO> list= noticeService.list(pager);
		model.addAttribute("peger",pager);
		model.addAttribute("list", list);
		return "board/list";
		
	}
	
	@GetMapping("detail")
	public String detail(NoticeVO noticeVO,Model model)throws Exception{
		
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("detail",boardVO);
		return "board/detail";
		
	}
	@GetMapping("add")
	public String add(@ModelAttribute("boardVO") BoardVO noticeVO)throws Exception {
		return "board/add";
	}
	@PostMapping("add")
	public String add(@Valid BoardVO noticeVO,BindingResult bindingResult,MultipartFile[] attaches, HttpSession session)throws Exception {
		if(bindingResult.hasErrors()) {
			return "board/add";
		}
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		noticeVO.setBoardWriter(memberVO.getUsername());
		int result =noticeService.insert(noticeVO,attaches);
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(NoticeVO noticeVO ,Model model)throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		
		return"board/add";
	}
	@PostMapping("update")
	public String update(NoticeVO noticeVO,MultipartFile[] attaches,Model model)throws Exception {
		int result = noticeService.update(noticeVO,attaches);
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
	
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int fileDelete(Model model,BoardFileVO boardfileVO)throws Exception{
		int result = noticeService.filedelete(boardfileVO);
		return result;
		
		
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileVO boardfileVO ,Model model)throws Exception{
		boardfileVO= noticeService.fileDetail(boardfileVO);
		model.addAttribute("vo",boardfileVO);
		return "fileDownView";
	}
	@ResponseBody	
	@PostMapping("boardFile")
	public String boardFile(MultipartFile bf,Model model)throws Exception{
		
		return noticeService.boardFile(bf);
	}
	@ResponseBody
	@PostMapping("boardFileDelete")
	public boolean boardFileDelete(String fileName)throws Exception{
		return noticeService.boardFileDelete(fileName);
		
	}
	
}
