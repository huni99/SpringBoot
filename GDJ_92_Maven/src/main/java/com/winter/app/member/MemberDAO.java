package com.winter.app.member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	//회원정보 
	public int insert(MemberVO memberVO) throws Exception;
	//회원 프로필
	public int insertFile(ProfileVO memberProfile)throws Exception;
	
	
}
