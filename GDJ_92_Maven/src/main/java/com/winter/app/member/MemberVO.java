package com.winter.app.member;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO implements UserDetails{
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
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private ProfileVO memberProfile;
	private List<RoleVO> roleVOs;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			list.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
			
		}
		return list;
	}

}
