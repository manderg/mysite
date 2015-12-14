package com.hanains.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.GuestbookDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.GuestbookVo;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// default(idnex) action
		GuestbookDao dao= new GuestbookDao();
		List<GuestbookVo> list = dao.getList();

		request.setAttribute( "list", list );
		WebUtil.forwarding( request, response, "/WEB-INF/views/guestbook/list.jsp" );		
	}

}
