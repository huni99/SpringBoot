package com.winter.app.member;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member_role")
@Getter
@Setter
@ToString
//@IdClass(MemberRolePK.class)

public class MemberRoleVO {
//	@Id
//	private String username;
//	@Id
//	private Long roleNum;
	
	//@EmbeddedId
	//private MemberRolePK memberRolePK; 
	@ManyToOne
	@Id
	@JoinColumn(name = "username",insertable = false , updatable = false)
	private MemberVO memberVO;
	
	@Id
	@ManyToOne
	@JoinColumn(name="roleNum" , insertable = false , updatable = false)
	private RoleVO roleVO;
}
