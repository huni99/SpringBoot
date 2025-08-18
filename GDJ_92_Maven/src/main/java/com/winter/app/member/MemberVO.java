package com.winter.app.member;



import java.time.LocalDate;
import java.util.List;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO {
	@NotBlank(message = "ID는필수",groups = AddGroup.class)
	private String username;
	
	@Size(min = 6, max=12,groups = AddGroup.class)
	private String password;
	
	private String passwordCheck;
	@NotBlank(groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	@Email(groups = {AddGroup.class,UpdateGroup.class})
	private String email;
//	@Pattern(regexp = "")
	private String phone;
	@Past(groups = {AddGroup.class,UpdateGroup.class})
	private LocalDate birth;
	private ProfileVO memberProfile;
	private List<RoleVO> roleVOs;
}
