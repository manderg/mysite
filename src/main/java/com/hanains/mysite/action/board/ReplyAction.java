package com.hanains.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.util.WebUtil;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.MemberVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( response, "/mysite/main" );
			return;
		}
		MemberVo memberVo = (MemberVo)session.getAttribute( "authUser" );
		Long no = new Long( request.getParameter( "no" ) );
		Long memberNo = memberVo.getNo();		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		Long grp_no = new Long(request.getParameter("grp_no"));
		Long seq_no = new Long(request.getParameter("seq_no"));
		Long lvl = new Long(request.getParameter("lvl"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent( content );
		vo.setMemberNo( memberNo );
		vo.setGrp_no(grp_no);
		vo.setSeq_no(seq_no);
		vo.setLvl(lvl);
		
		BoardDao dao = new BoardDao();
		dao.replyupdate(vo);
		
		WebUtil.redirect( response, "/mysite/board" );
	}
}
