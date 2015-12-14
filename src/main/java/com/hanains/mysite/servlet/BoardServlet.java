package com.hanains.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.action.ActionFactory;
import com.hanains.mysite.action.board.BoardActionFactory;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		
		String actionName = request.getParameter( "a" );
		ActionFactory actionFactory = new BoardActionFactory();
		
		Action action = actionFactory.getAction( actionName );
		action.execute(request, response);
	}
}
