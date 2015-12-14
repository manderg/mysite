package com.hanains.mysite.action.board;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if( "view".equals( actionName ) ) {
			action = new ViewAction();
		} else if( "write".equals( actionName ) ) {
			action = new WriteAction();
		} else if( "modify".equals( actionName ) ) {
			action = new ModifyAction();
		} else if( "insert".equals( actionName ) ) {
			action = new InsertAction();
		} else if( "delete".equals( actionName ) ) {
			action = new DeleteAction();
		} else if( "update".equals( actionName ) ) {
			action = new UpdateAction();
		} else if( "reply".equals( actionName ) ) {
			action = new ReplyAction();
		} else if( "replyform".equals( actionName ) ) {
			action = new ReplyFormAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
