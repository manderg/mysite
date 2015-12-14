package com.hanains.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.BoardVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = null;
		Long length;
		Long page = (long)1;
		
		if(request.getParameter("page")!=null){
			System.out.println("page값 없으니 1로 설정");
			page = new Long(request.getParameter("page"));
		}
		if(request.getParameter("kwd")!=null){
			String category = request.getParameter("category");
			if(category==null){
				category = "title";
			}
			String key = request.getParameter("kwd");
			System.out.println("cate :" + category + "    key: " + key);
			list = dao.getList(category,key,page);
			length = dao.getLength(category, key); 
			
		}else{
			list = dao.getList(page);
			length = dao.getLength();
		}
		request.setAttribute( "list", list );
		request.setAttribute( "length", length/10 + 1);
		WebUtil.forwarding( request, response, "/WEB-INF/views/board/list.jsp" );
	}
}