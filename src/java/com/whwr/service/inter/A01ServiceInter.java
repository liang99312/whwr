package com.whwr.service.inter;

import com.whwr.service.base.BaseServiceInter;
import com.whwr.domain.A01;

public interface A01ServiceInter extends BaseServiceInter {
	
	public A01 checkLogin(String name,String password);
	
	public void deleteA01(int id);
	
}
