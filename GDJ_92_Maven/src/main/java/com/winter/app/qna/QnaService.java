package com.winter.app.qna;

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
public class QnaService implements BoardService {
	@Autowired
	QnaDAO qnaDao;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;
	@Value("${board.qna}")
	private String board;

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return 	qnaDao.delete(boardVO);
		
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDao.detail(boardVO);
	}

	@Override
	public int insert(BoardVO boardVO, MultipartFile[] attaches) throws Exception {

		int result = qnaDao.insert(boardVO);
		// ref값을 update
		result = qnaDao.refUpdate(boardVO);
		// 1. File을 HDD에 저장
		if (attaches == null) {
			return result;
		}
		for (MultipartFile f : attaches) {
			if (f == null || f.isEmpty())
				continue;
			String fileName = fileManager.fileSave(upload + board, f);

			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(f.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(boardVO.getBoardNum());
			result = qnaDao.insertFile(boardFileVO);

		}
		return result;
	}

	@Override
	public int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		int result = qnaDao.update(boardVO);
		if (attaches == null) {
			return result;
		}

		for (MultipartFile f : attaches) {
			if (f == null || f.isEmpty())
				continue;
			String saveName = fileManager.fileSave(upload + board, f);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setBoardNum(boardVO.getBoardNum());
			boardFileVO.setOriName(f.getOriginalFilename());
			boardFileVO.setSaveName(saveName);
			result = qnaDao.insertFile(boardFileVO);

		}
		return result;
	}

	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDao.totalCount(pager);
		pager.makeNum(totalCount);
		return qnaDao.list(pager);
	}

	public int reply(QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		QnaVO parent = (QnaVO) qnaDao.detail(qnaVO);
		int result = qnaDao.replyUpdate(parent);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		result = qnaDao.insert(qnaVO);
		for (MultipartFile f : attaches) {
			if (f == null || f.isEmpty())
				continue;
			String saveName = fileManager.fileSave(upload + board, f);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setBoardNum(qnaVO.getBoardNum());
			boardFileVO.setOriName(f.getOriginalFilename());
			boardFileVO.setSaveName(saveName);
			result = qnaDao.insertFile(boardFileVO);

		}

		return result;
	}

	@Override
	public int filedelete(BoardFileVO boardFileVO) throws Exception {
		// 1. File 조회
		boardFileVO = qnaDao.fileDetail(boardFileVO);
		// 2. File 삭제
		boolean result = fileManager.fileDelete(upload + board, boardFileVO.getSaveName());
		// 3. DB 삭제
		return qnaDao.fileDeleteOne(boardFileVO);

	}
	@Override
	public BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception {
		return qnaDao.fileDetail(boardFileVO);
	}
	
	@Override
	public String boardFile(MultipartFile multipartFile) throws Exception {
		if(multipartFile ==null || multipartFile.getSize()==0) {
			return null;
		}
		String filename=fileManager.fileSave(upload+board, multipartFile);
		
		return "/files/"+board+"/"+filename;
	}
	
	@Override
	public boolean boardFileDelete(String fileName) throws Exception {
		int index = fileName.lastIndexOf("/");
		String oriName = fileName.substring(index+1);
		return fileManager.fileDelete(upload+board,oriName);
	}
}
