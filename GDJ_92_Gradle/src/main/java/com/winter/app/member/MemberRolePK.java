package com.winter.app.member;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * *****복합키 만드는 방식*****
 * Serializable 인터페이스 구현
 * equals, hashcode overriding
 * 기본생성자 필수
 * 클래스는 public 접근지정자
 * 
 *
 **/
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class MemberRolePK implements Serializable{
	
	private String username;
	private Long roleNum;
}
