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
		Long totalCount = noticeDAO.totalCount(pager);
		
		pager.makeNum(totalCount);
		return noticeDAO.list(pager);
	}

	@Override
	public BoardVO detail(BoardVO boardVo) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.detail(boardVo);

	}
	
	@Override
	public int insert(BoardVO boardVO,MultipartFile []attaches) throws Exception {
		int result =noticeDAO.insert(boardVO);
		//1. File을 HDD에 저장
		if(attaches==null) {
			return result;
		}
		for(MultipartFile m :attaches) {
			if(m == null || m.isEmpty()) {
				continue;
			}
			
		
		
		String fileName = fileManager.fileSave(upload+board, m);
		
		//2. 저장된 파일의 정보를 DB에 저장
		BoardFileVO boardFileVO=new BoardFileVO();
		boardFileVO.setOriName(m.getOriginalFilename());
		boardFileVO.setSaveName(fileName);
		boardFileVO.setBoardNum(boardVO.getBoardNum());
		result = noticeDAO.insertFile(boardFileVO);
		}
		return result; 
	}
	@Override
	public int update(BoardVO boardVO , MultipartFile[] attaches) throws Exception {
		//1.파일을 HDD에 저장
		int result= noticeDAO.update(boardVO);
		if(attaches==null) {
			return result;
		}
		for(MultipartFile f : attaches) {
			if(f==null||f.isEmpty())continue;
			
			String saveName= fileManager.fileSave(upload+board,f);
			//2. 파일 정보를 FileDB에 저장
			BoardFileVO boardFileVO= new BoardFileVO();
			boardFileVO.setOriName(f.getOriginalFilename());
			boardFileVO.setBoardNum(boardVO.getBoardNum());
			boardFileVO.setSaveName(saveName);
			result =noticeDAO.insertFile(boardFileVO);
		
		}
			
		return result;
		
		
	}
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.detail(boardVO);
		for(BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload+board, vo.getSaveName());
		int result = noticeDAO.fileDelete(boardVO);
		}
		return noticeDAO.delete(boardVO);
	}
	@Override
	public int filedelete(BoardFileVO boardFileVO) throws Exception {
			//1. File 조회
			boardFileVO=noticeDAO.fileDetail(boardFileVO);
			//2. File 삭제
			boolean result=fileManager.fileDelete(upload+board, boardFileVO.getSaveName());
			//3. DB 삭제
			return noticeDAO.fileDeleteOne(boardFileVO);
			
	}
}
