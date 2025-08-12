package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	
	@GetMapping("join")
	public void memberJoin()throws Exception {
		
	}
	@PostMapping("join")
	public String memberJoin(MemberVO memberVO , MultipartFile profile)throws Exception  {
		int result= memberService.insert(memberVO, profile);
		return "redirect:/";
	}
	
}
