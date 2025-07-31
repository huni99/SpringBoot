package com.winter.app.board;

import com.winter.app.board.notice.NoticeVO;

public interface BoardDao {
	
	//detail
	public BoardVO detail(BoardVO vo )throws Exception;
	//insert
	public int insert(BoardVO vo)throws Exception;
	//update
	public int update(BoardVO vo)throws Exception;
	//delete
	public int delete(int boardNum)throws Exception;
}
