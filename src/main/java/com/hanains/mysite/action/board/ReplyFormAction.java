package com.hanains.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.BoardVo;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long no = new Long( request.getParameter( "no" ) );
		
		BoardDao dao = new BoardDao();
		// 게시물 가져오기
		BoardVo vo = dao.get( no );
		request.setAttribute( "board", vo );
		WebUtil.forwarding(request, response, "/WEB-INF/views/board/write_reply.jsp");
	}
}
