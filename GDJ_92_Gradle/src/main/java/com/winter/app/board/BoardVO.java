package com.winter.app.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Sort;

import com.winter.app.board.notice.NoticeVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BoardVO {
	@Id //PK로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNum;
	
	
	@Column(name="boardTitle",nullable = true,unique = false,length = 255)
	private String boardTitle;
	private String boardWriter;
	//@Column(columnDefinition = "LONGTEXT")
	@Lob// 위에랑 같은 뜻
	private String boardContents;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private LocalDateTime boardDate;
	@Column(columnDefinition = "BIGINT DEFAULT 0" ,insertable = false)
	@ColumnDefault(value="0")
	private Long boardHit;
	@Transient
	private String kind;
}

