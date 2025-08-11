package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;


@RequestMapping(value="/qna/*")
@Controller
public class QnaController {
	@Value("${board.qna}")
	private String name;
	@Autowired
	QnaService qnaService;
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception {
		List<BoardVO> list= qnaService.list(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager",pager);
		return "board/list";
	}
	@GetMapping("add")
	public String insert()throws Exception {
		return "board/add";
	}
	@PostMapping("add")
	public String insert(QnaVO qnaVO, MultipartFile[] attaches)throws Exception{
		int result = qnaService.insert(qnaVO,attaches);
		return "redirect:./list";
	}
	@GetMapping("detail")
	public String detail(QnaVO qnaVO, Model model)throws Exception{
		BoardVO vo = qnaService.detail(qnaVO);
		model.addAttribute("detail", vo);
		return "board/detail";
	}
	@GetMapping("reply")
	public String reply(QnaVO qnaVO,Model model)throws Exception{
		model.addAttribute("detail",qnaVO);
		return "board/add";
	}
	@PostMapping("reply")
	public String reply(QnaVO qnaVO,MultipartFile[] attaches)throws Exception{
		qnaService.reply(qnaVO, attaches);
		return "redirect:./list";
	}
	@GetMapping("update")
	public String update(Model model,QnaVO qnaVO) throws Exception {
		BoardVO boardVO = qnaService.detail(qnaVO);
		model.addAttribute("boardVO", boardVO);
		
		return"board/add";
	}
	@PostMapping("update")
	public String update(Model model, MultipartFile[] attaches, QnaVO qnaVO) throws Exception{
		int result = qnaService.update(qnaVO, attaches);
		String msg = "수정 실패";
		
		if(result>0) {
			msg="수정 성공";
		}
		
		String url="./detail?boardNum="+qnaVO.getBoardNum();
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "commons/result";   //"redirect:/notice/detail?boardNum="+noticeVO.getBoardNum();
	}
	@PostMapping("fileDelete")
	@ResponseBody
	public int fileDelete(Model model, BoardFileVO boardfileVO)throws Exception {
		int result = qnaService.filedelete(boardfileVO);
		return result;
		
	}
	@PostMapping("delete")
	public String delete(QnaVO qnaVO,Model model)throws Exception{
		int result =qnaService.delete(qnaVO);
		String msg = "삭제 실패";
		String url ="./list";
		if(result>0) {
			msg="삭제 성공";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "commons/result";
	}
	@GetMapping("fileDown")
	public String fileDown(BoardFileVO boardFileVO ,Model model) throws Exception{
		boardFileVO = qnaService.fileDetail(boardFileVO);
		model.addAttribute("vo",boardFileVO);
		return "fileDownView";
	}
	@ResponseBody	
	@PostMapping("boardFile")
	public String boardFile(MultipartFile bf,Model model)throws Exception{
		
		return qnaService.boardFile(bf);
	}
	@ResponseBody
	@PostMapping("boardFileDelete")
	public boolean boardFileDelete(String fileName)throws Exception{
		return qnaService.boardFileDelete(fileName);
		
	}
	
}
