package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;


@Service
public class QnaService implements BoardService {
	@Autowired
	QnaDAO qnaDao;
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return 0;
	}
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDao.detail(boardVO);
	}
	@Override
	public int insert(BoardVO boardVO) throws Exception {
		int result = qnaDao.insert(boardVO);
		//ref값을 update
		result=qnaDao.refUpdate(boardVO);
		
		return result;
	}
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return 0;
	}
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDao.totalCount();
		pager.makeNum(totalCount);
		return qnaDao.list(pager);
	}
	
	public int reply(QnaVO qnaVO)throws Exception{
		QnaVO parent = (QnaVO)qnaDao.detail(qnaVO);
		int result=qnaDao.replyUpdate(parent);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardDepth(parent.getBoardDepth()+1);
		qnaVO.setBoardStep(parent.getBoardStep()+1);
		result= qnaDao.replyInsert(qnaVO);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
