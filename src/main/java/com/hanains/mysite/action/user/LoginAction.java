package com.hanains.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.MemberDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.MemberVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		
		MemberDao dao = new MemberDao();
		
		MemberVo vo = dao.get(email, password);
		
		//로그인 실패
		if( vo == null ) {
			//실패 처리1
			//WebUtil.forwarding(request, response, "/WEB-INF/views/user/loginform_error.jsp");
			//실패 처리2
			WebUtil.redirect( response, "/mysite/user?a=loginform&result=error" );
			return;
		}
		
		//로그인 처리
		HttpSession session = request.getSession( true );
		session.setAttribute( "authUser", vo );
		
		// main redirection
		WebUtil.redirect( response, "/mysite/main" );
	}
}