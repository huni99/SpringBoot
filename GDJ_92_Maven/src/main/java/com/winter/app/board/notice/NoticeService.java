package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
@Service
public class NoticeService implements BoardService{
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<BoardVO> list() throws Exception {
		return noticeDAO.BoardList();
	}
   @Override
   public BoardVO detail(BoardVO boardVo) throws Exception {
	// TODO Auto-generated method stub
	return noticeDAO.detail(boardVo);
}
}
