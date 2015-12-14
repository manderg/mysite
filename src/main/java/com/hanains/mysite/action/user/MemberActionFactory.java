package com.hanains.mysite.action.user;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.action.ActionFactory;
import com.hanains.mysite.action.guestbook.IndexAction;

public class MemberActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if( "joinform".equals( actionName ) ) {
			action = new JoinFormAction();
		} else if( "joinsuccess".equals( actionName ) ) {
			action = new JoinSuccessAction();
		} else if( "join".equals( actionName ) ) {
			action = new JoinAction();
		} else if( "loginform".equals( actionName ) ) {
			action = new LoginFormAction();
		} else if( "login".equals( actionName ) ) {
			action = new LoginAction();
		} else if( "logout".equals( actionName ) ) {
			action = new LogoutAction();
		} else if( "checkemail".equals( actionName ) ) {
			action = new CheckEmailAction();
		} else {
			// error 처리 
			action = new IndexAction();
		}
		
		return action;
	}

}
