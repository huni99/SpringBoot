package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.product.products.ProductVO;

@Transactional(rollbackFor=Exception.class)
@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.member}")
	private String board;
	
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
	

}
