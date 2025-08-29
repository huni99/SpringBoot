package com.winter.app.board.notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/notice/**")
@RestController
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	
	//페이징 처리 관련 참고 https://juntcom.tistory.com/219
	public Page<NoticeVO> list(@PageableDefault(sort="boardNum",direction =Sort.Direction.DESC) Pageable pageable)throws Exception{
		
		return noticeService.list(pageable);
	}
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum)throws Exception {
		NoticeVO noticeVO = noticeService.detail(boardNum);
		return noticeService.detail(boardNum);
	}
	
}
