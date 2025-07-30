package com.winter.app.board;

public interface BoardDao {
	
	//insert
	public int insert(BoardVO vo)throws Exception;
	//update
	public int update(BoardVO vo)throws Exception;
	//delete
	public int delete(int boardNum)throws Exception;
}
