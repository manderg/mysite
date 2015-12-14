package com.hanains.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.GuestbookDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.GuestbookVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String name = request.getParameter( "name" );
		String password = request.getParameter( "pass" );
		String message = request.getParameter( "content" );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		GuestbookDao dao = new GuestbookDao();
		dao.insert(vo);
		
		WebUtil.redirect( response, "/mysite/guestbook" );
	}

}
