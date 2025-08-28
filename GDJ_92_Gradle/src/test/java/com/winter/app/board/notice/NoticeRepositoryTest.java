package com.winter.app.board.notice;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.board.BoardVO;


@SpringBootTest
//@Transactional
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;
	@Test
	void test() {
		NoticeVO boardVO = new NoticeVO();
		boardVO.setBoardTitle("title1");
		boardVO.setBoardContents("contents1");
		boardVO.setBoardWriter("Writer1");
		
		boardVO = noticeRepository.save(boardVO);
		assertNotNull(boardVO);
	}
	
	@Test
	void test2() {
		Optional<NoticeVO> result =noticeRepository.findById(1L);
		BoardVO boardVO = result.get();
		System.out.println(boardVO.getBoardTitle());
		
		
		assertNotNull(boardVO);
	}
}
