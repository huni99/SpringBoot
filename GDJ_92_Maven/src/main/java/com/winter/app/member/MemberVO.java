package com.winter.app.member;



import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberVO {
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private LocalDate birth;
	private ProfileVO memberProfile;
}
