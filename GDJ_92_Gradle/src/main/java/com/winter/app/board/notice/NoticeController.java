package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notice/**")
@RestController
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	public List<NoticeVO> list()throws Exception{
		return noticeService.list();
	}
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum)throws Exception {
		return noticeService.detail(boardNum);
	}
	
}
