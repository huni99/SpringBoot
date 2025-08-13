package com.winter.app.filters;

import java.io.IOException;
import java.util.List;

import com.winter.app.member.MemberVO;
import com.winter.app.member.RoleVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AdminCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean check = false;
		if (session != null) {
			
			MemberVO memberVO = (MemberVO) session.getAttribute("member");
			if (memberVO != null) {
				List<RoleVO> list = memberVO.getRoleVOs();
				for (RoleVO r : list) {
					if (r.getRoleName().equals("ROLE_ADMIN")) {
						check = true;
						break;
					}
				}
			}
		}
		if (check == false) {
			req.setAttribute("msg", "권한 없음");
			req.setAttribute("url", "/");
			req.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
			
		}else {
			chain.doFilter(request, response);
	
			
		}


	}
}
