package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	@Test
	void test1() {
		
		MemberVO memberVO = new MemberVO();
		MemberRoleVO memberRoleVO =new MemberRoleVO();
		RoleVO roleVO = new RoleVO();
		memberVO.setName("member1");
		memberVO.setUsername("member1");
		memberVO.setPassword("member1");
		memberVO.setEmail("22@naver.com");
		memberVO.setBirth(LocalDate.now());
		roleVO.setRoleNum(1l);
		memberRoleVO.setMemberVO(memberVO);
		memberRoleVO.setRoleVO(roleVO);
		List<MemberRoleVO>list =new ArrayList<>();
		list.add(memberRoleVO);
		
		memberVO.setMemberRoleVO(list);
		roleVO.setMemberRoleVO(list);
		memberRepository.save(memberVO);
		

	}
}
