package com.winter.app.board.notice;

import com.winter.app.board.BoardVO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity //해당 객체가 JPA에서 관리하고 있다라는 것을 정의
@Table(name="notice")//DB에 존재하는 테이블 이름을 매핑, 클래스명이 테이블명이 됨

public class NoticeVO extends BoardVO{
	
}
