package com.winter.app.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UpdateWriterCheckInterceptor implements HandlerInterceptor{

	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().toUpperCase().equals("POST")) {
			return;
		}
		MemberVO memberVO= (MemberVO)request.getSession().getAttribute("member");
		BoardVO boardVO = (BoardVO)modelAndView.getModel().get("boardVO");
		if(!memberVO.getUsername().equals(boardVO.getBoardWriter())) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("msg","작성자만 가능");
			modelAndView.addObject("url","./list");
//			request.setAttribute("msg", "권한없음");
//			request.setAttribute("url","/");
//			request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
		}
	}
}
