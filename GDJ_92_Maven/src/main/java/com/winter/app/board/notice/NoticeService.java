package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.notice}")
	private String board;
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = noticeDAO.totalCount();
		
		pager.makeNum(totalCount);
		return noticeDAO.list(pager);
	}

	@Override
	public BoardVO detail(BoardVO boardVo) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.detail(boardVo);

	}
	
	@Override
	public int insert(BoardVO boardVO,MultipartFile attaches) throws Exception {
		noticeDAO.insert(boardVO);
		//1. File을 HDD에 저장
		
		String fileName = fileManager.fileSave(upload+board, attaches);
		
		//2. 저장된 파일의 정보를 DB에 저장
		BoardFileVO boardFileVO=new BoardFileVO();
		boardFileVO.setOriName(attaches.getOriginalFilename());
		boardFileVO.setSaveName(fileName);
		boardFileVO.setBoardNum(boardVO.getBoardNum());
		int result = noticeDAO.insertFile(boardFileVO);
		return 0; 
	}
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		
		return noticeDAO.delete(boardVO);
	}
}
