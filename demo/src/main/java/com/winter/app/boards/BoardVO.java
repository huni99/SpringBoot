package com.winter.app.boards;

import lombok.Data;

@Data
public class BoardVO {

	private Integer num;
	private String name;
	private String title;
	
	public void setKind(String num) {
		this.title=num;
			
		
	}
}
