package com.winter.app.member;



import java.time.LocalDate;
import java.util.List;

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
	private List<RoleVO> roleVOs;
}
