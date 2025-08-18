package com.winter.app.board;

import java.time.LocalDate;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class BoardVO {
	private Long boardNum;
	//null을 허용하지 않고 최소 문자 한개이상
	@NotBlank
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDate boardDate;
	private Long boardHit;
	private List<BoardFileVO> boardFileVOs;
}
