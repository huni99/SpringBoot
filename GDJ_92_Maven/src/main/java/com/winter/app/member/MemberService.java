package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;

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
		return result;
	}

}
