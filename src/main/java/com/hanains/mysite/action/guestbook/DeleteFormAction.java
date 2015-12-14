package com.hanains.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String no = request.getParameter( "no" );
		//request.setAttribute( "no", no );
		WebUtil.forwarding( request, response, "/WEB-INF/views/guestbook/deleteform.jsp" );
	}

}
