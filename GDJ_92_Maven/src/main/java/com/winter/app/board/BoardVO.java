package com.winter.app.board;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import lombok.Data;
@Data
@Repository
public class BoardVO {
	private Long boardNum;
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDate boardDate;
	private Long boardHit;
	
}
