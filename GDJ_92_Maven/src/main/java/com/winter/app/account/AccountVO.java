package com.winter.app.account;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class AccountVO {
	
	private String accountNum =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
	private String username;
	private Long productNum;
	private LocalDate Date;
	private Long accountBalance;
	
}
