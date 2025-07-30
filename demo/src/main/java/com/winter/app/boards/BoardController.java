package com.winter.app.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService;
	// /board/list =>
	
	public BoardController() {
		this.boardService = new BoardService();
	}
	@RequestMapping(value="list", method=RequestMethod.GET )
	public String boardList() {
		System.out.println("목록조회");
		boardService.list();
		return "board/list";
		
		
		
	}
	// /board/detail=>
	@RequestMapping(value="detail",method=RequestMethod.GET)
	public void boardDetail(BoardVO boardVO) {
//		int num = Integer.parseInt(request.getParameter("num"));
//		System.out.println("detail: "+num+" "+kind );
//		BoardVO boardVO = new BoardVO();
//		boardVO.setName(kind);
//		boardVO.setNum(num);
				
		System.out.println(boardVO);
	}
	
	
}
