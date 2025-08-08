package com.winter.app.qna;

import com.winter.app.board.BoardVO;

import lombok.Data;


@Data
public class QnaVO extends BoardVO {
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	
	public Long getBoardRef() {
		if(this.boardRef==null) {
			this.boardRef=0L;
		}
			
		return this.boardRef;
	}
	public Long getBoardStep() {
		if(this.boardStep==null) {
			this.boardStep=0L;
		}
			
		return this.boardStep;
	}
	public Long getBoardDepth() {
		if(this.boardDepth==null) {
			this.boardDepth=0L;
		}
			
		return this.boardDepth;
	}
}
