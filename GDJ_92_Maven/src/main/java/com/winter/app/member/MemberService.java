package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.product.products.ProductVO;

@Transactional(rollbackFor=Exception.class)
@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.member}")
	private String board;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		username
		return null;
	}
	
	//검증 메서드
	public boolean hasMemberError(MemberVO memberVO , BindingResult bindingResult)throws Exception {
		boolean check = false;
		//check 가 true이면 검증 실패 (문제가 있음)
		
		//1. Annotation 검증
		check=bindingResult.hasErrors();
		
		//2. 사용자 정의로 패스워드가 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		//3.ID 중복 검사
		memberVO = memberDAO.detail(memberVO);
		if(memberVO!=null) {

			check = true;
			bindingResult.rejectValue("username","member.id.duplicate");
		}
		return check;
	}
	
	public int insert(MemberVO memberVO, MultipartFile profile)throws Exception {
		
			int result = memberDAO.insert(memberVO);
			
			ProfileVO memberProfile = new ProfileVO();
			String fileName="Default.jpg";
			
			memberProfile.setOriName(fileName);
			memberProfile.setSaveName(fileName);
			memberProfile.setUsername(memberVO.getUsername());
			
			if (profile != null && !profile.isEmpty()) {
				 fileName = fileManager.fileSave(upload+board, profile);
				 memberProfile.setOriName(profile.getOriginalFilename());
				 memberProfile.setSaveName(fileName);
			}
			result = memberDAO.insertFile(memberProfile);
			
			Map<String, Object> map = new HashMap<>();
			map.put("username",memberVO.getUsername());
			map.put("roleNum",3);
			
			result=memberDAO.addRole(map);
		return result;
	}

	public MemberVO detail(MemberVO memberVO)throws Exception {
		
	 return	memberDAO.detail(memberVO);
		
	}

	public MemberVO check(MemberVO memberVO)throws Exception {
		
		memberVO=memberDAO.check(memberVO);
		
		return memberVO;
		
	}

	public int cartAdd(Map<String, Object> map) throws Exception{
		return memberDAO.cartAdd(map);
		
	}

	public List<ProductVO> cartList(MemberVO username) throws Exception{
		
		return memberDAO.cartList(username);
	}

	public int cartDelete(MemberVO memberVO, Long[] productNum)throws Exception {
		int result = 0;
		Map<String , Object>map = new HashMap<>();
		for(Long p : productNum) {
			map.put("productNum", p);
			map.put("username", memberVO);
			result=memberDAO.cartDelete(map);
		}
		
		return result;
	}

	public int update(MemberVO memberVO)throws Exception {
			
		return memberDAO.update(memberVO);
	}
	

}
