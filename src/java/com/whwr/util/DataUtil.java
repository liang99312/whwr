package com.whwr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import com.whwr.domain.A01;
import com.whwr.service.impl.A01ServiceImpl;
import com.whwr.service.inter.A01ServiceInter;

public class DataUtil {
	
	public static boolean a01ChangeFlag = false;
	public static List<A01> a01s = new ArrayList<A01>();
	public static List<Integer> upIds = new ArrayList<Integer>();
	public static HashMap<Integer,Integer> upA01Ids = new HashMap<Integer,Integer>();
	public static HashMap<Integer,Long> loginIds = new HashMap<Integer,Long>();
	public static HashMap<Integer,String> fingerMap = new HashMap<Integer,String>();
	
	public static void getA01sFromDb(){
		A01ServiceInter a01ServiceInter = (A01ServiceImpl) ApplicationUtil.getBean("a01ServiceImpl");
		a01s.clear();
		a01s.addAll((List<A01>)a01ServiceInter.getResult("from A01 a01", null));
	}
	
	public static List getA01Data(int id){
		loginIds.put(id, System.currentTimeMillis());
		List<A01> a01List = new ArrayList();
		List<Integer> idList = new ArrayList();
		List result = new ArrayList();
		result.add(a01List);
		result.add(idList);
		if(a01s.isEmpty() || a01ChangeFlag){
			getA01sFromDb();
			a01ChangeFlag = false;
		}
		Integer flag = upA01Ids.get(id);
		if(flag == null){
			upA01Ids.put(id, 0);
			idList.addAll(upIds);
			a01List.addAll(a01s);		
		}else{
			if(flag == 0){
			}else if(flag == 1){
				idList.addAll(upIds);
				upA01Ids.put(id, 0);
			}else if(flag == 2){
				idList.addAll(upIds);
				a01List.addAll(a01s);
				upA01Ids.put(id, 0);
			}
		}
		return result;
	}
	
	public static void upA01Id(int id){
		if(!upIds.contains(id)){
			upIds.add(id);
		}
	}
	
	public static void removeA01Id(int id){
		upIds.remove(id);
	}
	
	public static void setA01Updates(int value,boolean flag){
		a01ChangeFlag = flag;
		Set<Integer> keySet = upA01Ids.keySet();
		for(Integer i:keySet){
			upA01Ids.put(i, value);
		}
	}

	public static void checkLoginId(){
		Set<Integer> keySet = loginIds.keySet();
		Long l = System.currentTimeMillis();
		for(Integer i:keySet){
			Long t = loginIds.get(i);
			if(l-t>600000){
				loginIds.remove(i);
				upA01Ids.remove(i);
			}
		}
	}
	
	public static A01 getA01ById(int id){
		A01 a = null;
		for(A01 a01:a01s){
			if(a01.getId()==id){
				a = a01;
				break;
			}
		}
		return a;
	}
	
	public static void resetId(int id){
		upA01Ids.put(id, 2);
	}
}
