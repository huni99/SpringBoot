package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

    
	public String fileSave(String dir, MultipartFile attaches)throws Exception{
		//1. 디렉토리 생성
		File file = new File(dir);
		if(!file.exists()) {
			file.mkdirs();
		}
		//2. 저장할 파일명을 생성
		String fileName= UUID.randomUUID().toString();
		fileName = fileName+"_"+ attaches.getOriginalFilename();
		
		//3. HDD에 저장
		file = new File(file,fileName);
		
		//a.MultipartFile transferTo 메서드 사용
		//attaches.transferTo(file); 
		//b.FileCopyUtils의 copy 메서드 사용
		FileCopyUtils.copy(attaches.getBytes(),file);
		return fileName;
		
	}
	
	public boolean fileDelete(String dir, String fileName)throws Exception{
		File file = new File(dir,fileName);
		boolean result = false;
		if(file.exists()) {
			result =file.delete();
		System.out.println(result);
		}
		
		return result;		
	}
}
