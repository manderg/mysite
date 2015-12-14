package com.hanains.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Long no = new Long( request.getParameter( "no" ) );
		
		BoardDao dao = new BoardDao();
		dao.delete( no );
		
		WebUtil.redirect(response, "/mysite/board");
	}
}
