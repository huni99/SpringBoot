package com.winter.app.qna;


import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDao;
import com.winter.app.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDao{
	public int refUpdate(BoardVO boardVO)throws Exception;
	
	public int replyUpdate(QnaVO qnaVO)throws Exception;

	public int replyInsert(QnaVO qnaVO)throws Exception;
}
