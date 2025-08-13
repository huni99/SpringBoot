package com.winter.app.board;

import java.time.LocalDate;

import java.util.List;



import lombok.Data;
@Data
public class BoardVO {
	private Long boardNum;
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDate boardDate;
	private Long boardHit;
	private List<BoardFileVO> boardFileVOs;
}
