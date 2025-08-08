
package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;


public interface BoardDao {
	
	//detail
	public BoardVO detail(BoardVO vo )throws Exception;
	//insert
	public int insert(BoardVO vo)throws Exception;
	//update
	public int update(BoardVO vo)throws Exception;
	//delete
	public int delete(BoardVO vo)throws Exception;
	//list
	public List<BoardVO> list(Pager pager) throws Exception;
	//totalCount
	public Long totalCount(Pager pager) throws Exception;
	//insertFile
	public int insertFile(BoardFileVO boardFileVO)throws Exception;
	//filedelete
	public int fileDelete(BoardVO boardVO)throws Exception;
	
	
	public BoardFileVO fileDetail(BoardFileVO boardFileVO)throws Exception;
	public int fileDeleteOne(BoardFileVO boardFileVO)throws Exception;
}
