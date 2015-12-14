package com.hanains.mysite.action.main;

import com.hanains.mysite.action.Action;
import com.hanains.mysite.action.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = new IndexAction(); 
		return action;
	}

}
