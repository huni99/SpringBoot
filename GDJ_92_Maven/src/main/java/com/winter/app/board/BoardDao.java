package com.winter.app.board;

import java.util.List;


public interface BoardDao {
	
	//detail
	public BoardVO detail(BoardVO vo )throws Exception;
	//insert
	public int insert(BoardVO vo)throws Exception;
	//update
	public int update(BoardVO vo)throws Exception;
	//delete
	public int delete(int boardNum)throws Exception;
	//list
	public List<BoardVO> BoardList() throws Exception;
	
}
