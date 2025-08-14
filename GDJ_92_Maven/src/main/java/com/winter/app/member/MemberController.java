package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.product.products.ProductVO;

import jakarta.servlet.http.HttpSession;

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
	@GetMapping("login")
	public void memberLogin()throws Exception{}
	@PostMapping("login")
	public String memberLogin(MemberVO memberVO ,HttpSession session)throws Exception{
			
		MemberVO checkmemberVO = memberService.check(memberVO);
			if(checkmemberVO==null) {
				return "redirect:/";
			}
			memberVO = memberService.detail(memberVO);
			session.setAttribute("member", memberVO);
		return "redirect:/";
	}
	@GetMapping("logout")
	public String memberLogout(HttpSession session)throws Exception{
			session.removeAttribute("member");
		return "redirect:/";
	}
	@GetMapping("detail")
	public void memberDetail() throws Exception{
		
	}
	@PostMapping("cartAdd")
	@ResponseBody
	public int cartAdd(Long productNum , HttpSession session)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		Map<String,Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("productNum", productNum);
		
		int result = memberService.cartAdd(map);
		return result;
	}
	@GetMapping("cartList")
	public void cartList(HttpSession session , Model model)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		List<ProductVO> list = memberService.cartList(memberVO);
		model.addAttribute("list",list);
		
	}
	@PostMapping("cartList")
	public String cartListDelete(HttpSession session,Long[] productNum) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberService.cartDelete(memberVO,productNum);
		return "redirect:./cartList";
		
	}
}
