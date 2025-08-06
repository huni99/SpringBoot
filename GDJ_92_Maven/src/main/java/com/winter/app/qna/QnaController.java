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
	public String insert(QnaVO qnaVO)throws Exception{
		int result = qnaService.insert(qnaVO);
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
	public String reply(QnaVO qnaVO,Model model ,Integer i)throws Exception{
		qnaService.reply(qnaVO);
		return "redirect:./list";
	}
	
}
