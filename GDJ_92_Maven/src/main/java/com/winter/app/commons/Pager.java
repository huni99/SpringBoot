package com.winter.app.commons;


import lombok.Data;

@Data
public class Pager {
	
	//limit의 시작 인덱스 번호
	private Long startIndex;
	
	//limit의 끝 (limit 0 10 -> 0번째인데스부터 10개)
	private Long endIndex;
	//페이지당 보여줄 row 갯수(endIndex와 같음)
	private Long perPage;
	//page번호
	private Long pageNum;
	
	//검색종류
	private String kind;
	
	//검색어
	private String keyword;
	
	private Long totalPage;
	
	private Long startNum;
	private Long endNum;
	private void makePage() {
		//SQL의 Limit값을 계산
		
		this.startIndex=(this.getPageNum()-1)*this.getPerPage();
		this.endIndex = perPage;
	}
	public void makeNum(Long totalCount) {
		
		//1. totalPage : 전체 페이지 개수
		
		this.totalPage= (totalCount-1)/this.getPerPage()+1;
		
		//2. totalBlock : 전체 블럭의 갯수
		Long perBlock = 5L;//블럭당 출력할 번호의 개수
		
		Long totalBlock = (totalPage-1)/perBlock+1;
		
		//3. 현재 페이지 번호로 현재 블럭 번호를 계산
		Long curBlock = (this.getPageNum()-1)/perBlock+1;
		
		//4. 현재 블럭 번호로 시작번호와 끝번호 계산
		
		startNum= (curBlock-1)*perBlock+1;
		endNum=curBlock*perBlock;
		
		//5. 
		if(curBlock==totalBlock)this.endNum=totalPage;
		
		this.makePage();
	}
	
	public String getKeyword() {
		if(this.keyword==null)
		this.keyword="";
		return this.keyword;
	}
	
	public Long getPageNum() {
		if(this.pageNum==null || this.pageNum <=0) {
			this.pageNum=1L;
		}	
		if(this.pageNum>totalPage) {
			this.pageNum=totalPage;
		}
		return this.pageNum;
	}
	public Long getPerPage() {
		if(this.perPage==null) {
			this.perPage=10L;
		}
		return this.perPage;
	}
}
