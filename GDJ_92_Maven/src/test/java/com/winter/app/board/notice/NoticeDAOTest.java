package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDao;
	
	
//	@Test
//	void insertTest() throws Exception{
//		NoticeVO noticeVO =new NoticeVO();
//		noticeVO.setBoardContents("content3");
//		noticeVO.setBoardTitle("title3");
//		noticeVO.setBoardWriter("writer3");
//		int result = noticeDao.insert(noticeVO);
//		
//		
//		//단정문 
//		assertEquals(1, result);
//	}
//	@Test
//	void updateTest() throws Exception{
//		NoticeVO noticeVO =new NoticeVO();
//		noticeVO.setBoardContents("content4");
//		noticeVO.setBoardTitle("title4");
//		noticeVO.setBoardWriter("writer4");
//		int result = noticeDao.update(noticeVO);
//		
//		assertEquals(1, result);
//	
//	}
//	@Test
//	void deleteTest() throws Exception{
//		int boardNum=3;
//		int result = noticeDao.delete(boardNum);
//	
//		assertEquals(1, result);
//	
//	}
//	@Test
//	void detailTest() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardNum(1L);
//		BoardVO boardVO= noticeDao.detail(noticeVO);
//		log.info("result : {}",boardVO);
//		assertNotNull(boardVO);
//	}

}
