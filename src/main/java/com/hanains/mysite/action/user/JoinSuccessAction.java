package com.hanains.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.util.WebUtil;

public class JoinSuccessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebUtil.forwarding(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
	}
}
