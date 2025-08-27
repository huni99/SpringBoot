package com.winter.app.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.product.products.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackFor=Exception.class)
@Service
@Slf4j
public class MemberService   extends DefaultOAuth2UserService implements UserDetailsService{
	
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.member}")
	private String board;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		System.out.println("로그인 서비스");
		try {
			 memberVO=memberDAO.detail(memberVO);
			
			return memberVO;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("{}",userRequest.getAccessToken());
		log.info("{}",userRequest.getAdditionalParameters());
		log.info("{}",userRequest.getClientRegistration());
		
		String sns = userRequest.getClientRegistration().getRegistrationId();
		OAuth2User user=null;
		if(sns.toUpperCase().equals("KAKAO")) {
			
			user = this.useKakao(userRequest);
			
		}
		
		return user;
	}
	private OAuth2User useKakao(OAuth2UserRequest userRequest) {
		OAuth2User user = super.loadUser(userRequest);
		log.info("{}",user.getName());
		log.info("{}",user.getAttributes());
		log.info("{}",user.getAuthorities());
		Map<String,Object>map = user.getAttributes();
		LinkedHashMap<String, Object> m =(LinkedHashMap)map.get("properties");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(m.get("nickname").toString());
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		ProfileVO profileVO = new ProfileVO();
		profileVO.setSaveName(m.get("profile_image").toString());
		memberVO.setMemberProfile(profileVO);
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO= new RoleVO();
		
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		memberVO.setRoleVOs(list);
		
		memberVO.setAttributes(map);
		
		memberVO.setSns("kakao");
		
		return memberVO;
		
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
			
			System.out.println(memberVO.getPassword());
			memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
			int result = memberDAO.insert(memberVO);
			System.out.println(memberVO.getPassword());
			
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
