package com.hanains.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.MemberDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.MemberVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String name = request.getParameter( "name" );
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String gender = request.getParameter( "gender" );
		
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		MemberDao dao = new MemberDao();
		dao.insert( vo );
		
		WebUtil.redirect(response, "/mysite/user?a=joinsuccess");
	}

}
