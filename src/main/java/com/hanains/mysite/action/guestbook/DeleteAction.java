package com.hanains.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.GuestbookDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.GuestbookVo;

public class DeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String no = request.getParameter( "no" );
		String password = request.getParameter( "password" );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo( Long.parseLong( no ) );
		vo.setPassword(password);
		
		GuestbookDao dao = new GuestbookDao();
		dao.delete(vo);
		
		WebUtil.redirect( response, "/mysite/guestbook" );
	}
}
