package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.transaction.Transaction;

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
	
	@Autowired
	private Transaction transaction;
	
	public int insert(MemberVO memberVO, MultipartFile profile)throws Exception {
			transaction.t();
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
				 
				 if(profile != null) {
					 throw new Exception();
				 }
			}
			result = memberDAO.insertFile(memberProfile);
			transaction.t2();
		return result;
	}

}
