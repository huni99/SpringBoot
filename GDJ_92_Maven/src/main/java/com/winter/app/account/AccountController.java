package com.winter.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/account/*")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping("add")
	public String accountAdd(HttpSession session, Long[] productNum) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		accountService.accountAdd(memberVO,productNum);
		return "redirect:/";
	}
}
