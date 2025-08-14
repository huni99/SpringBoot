package com.winter.app.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.member.MemberDAO;
import com.winter.app.member.MemberVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private MemberDAO memberDAO;
	
	public int accountAdd(MemberVO memberVO, Long[]productNum ) throws Exception{
		int result=0;
		for(Long p : productNum) {
			AccountVO accountVO = new AccountVO();
			
			accountVO.setProductNum(p);
			accountVO.setUsername(memberVO.getUsername());
			result= accountDAO.accountAdd(accountVO);
			    Thread.sleep(10);
		}
		if(result>0) {
			Map<String , Object>map = new HashMap<>();
			for(Long p : productNum) {
				map.put("productNum", p);
				map.put("username", memberVO);
				memberDAO.cartDelete(map);
			}
		}
		return result;
	}
}
