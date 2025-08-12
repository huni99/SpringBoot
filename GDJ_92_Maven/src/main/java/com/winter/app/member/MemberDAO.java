package com.winter.app.member;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	//회원정보 
	public int insert(MemberVO memberVO) throws Exception;
	//회원 프로필
	public int insertFile(ProfileVO memberProfile)throws Exception;
	//
	public int addRole(Map<String,Object> map)throws Exception;
	
	public MemberVO detail(MemberVO memberVO )throws Exception;
	public MemberVO check(MemberVO memberVO)throws Exception;
}
