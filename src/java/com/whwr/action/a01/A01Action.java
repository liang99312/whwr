/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.whwr.action.a01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;

import com.whwr.domain.A01;
import com.whwr.service.inter.A01ServiceInter;
import com.whwr.util.DataUtil;
import com.whwr.util.DateJsonValueProcessor;
import com.whwr.action.BaseAction;


@Controller
public class A01Action extends BaseAction {
	@Resource
	private A01ServiceInter a01ServiceInter;

	private static final long serialVersionUID = 1L;

	public A01ServiceInter getA01ServiceInter() {
		return a01ServiceInter;
	}

	public void setA01ServiceInter(A01ServiceInter a01ServiceInter) {
		this.a01ServiceInter = a01ServiceInter;
	}
	
	public String execute() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAllA01s() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			List<A01> a01List = new ArrayList<A01>();
			a01List.addAll(DataUtil.a01s);
			if(a01List.isEmpty()){
				DataUtil.getA01sFromDb();
				a01List.addAll(DataUtil.a01s);
			}
			StringBuffer gridJson = new StringBuffer("{\"jls\":" + 0
					+",\"zys\":" + 0
					+",\"yx\":" + 0
					+ ",\"sz\":[");

			for (A01 g : a01List) {
				JsonConfig config = new JsonConfig();
				config.setIgnoreDefaultExcludes(false);
				config.registerJsonValueProcessor(java.util.Date.class,
						new DateJsonValueProcessor());
				JSONObject jsonObject = JSONObject.fromObject(g, config);
				if("bh".equals(jsonObject.optString("type"))){
					jsonObject.put("bh", g.getBh()+"(" + g.getMc() + ")");
				}else{
					jsonObject.put("bh", g.getMc()+"(" + g.getBh() + ")");
				}
				gridJson.append(jsonObject).append(",");
			}

			if (a01List.size() > 0) {
				gridJson.deleteCharAt(gridJson.length() - 1);
			}
			gridJson.append("]}");
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String goA01()throws ServletException, IOException {
		if(!existsUser()){
			return "login";
		}
		return "success";
	}  
	
	public String getA01s() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JSONObject jsonObj = getJsonObject();
			int pageNow = jsonObj.optInt("yx", 1);
			int pageSize = 20; 
			response.setCharacterEncoding("utf-8");
			String whereSql = " where (1=0 ";
			if(jsonObj.optString("bh")!=null&&!"".equals(jsonObj.optString("bh"))){
				whereSql += "or a01.bh like '" + jsonObj.optString("bh") + "%' ";
			}
			if(jsonObj.optString("mc")!=null&&!"".equals(jsonObj.optString("mc"))){
				whereSql += "or a01.mc like '" + jsonObj.optString("mc") + "%' ";
			}
			if(" where (1=0 ".equals(whereSql)){
				whereSql = "";
			}else{
				whereSql += ")";
			}
			int total = a01ServiceInter.getCount("select count(1) from A01"
					+ whereSql, null);
			System.out.println("pageNow=" + pageNow);
			int zys = (total-1)/20 + 1;
			List<A01> a01List = a01ServiceInter.getPageList("from A01 a01" + whereSql, null, pageNow, pageSize);
			StringBuffer gridJson = new StringBuffer("{\"jls\":" + total
					+",\"zys\":" + zys
					+",\"yx\":" + pageNow
					+ ",\"sz\":[");

			for (A01 g : a01List) {
				JsonConfig config = new JsonConfig();
				config.setIgnoreDefaultExcludes(false);
				config.registerJsonValueProcessor(java.util.Date.class,
						new DateJsonValueProcessor());
				JSONObject jsonObject = JSONObject.fromObject(g, config);
				gridJson.append(jsonObject).append(",");
			}

			if (a01List.size() > 0) {
				gridJson.deleteCharAt(gridJson.length() - 1);
			}
			gridJson.append("]}");
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLoginA01() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JsonConfig config = new JsonConfig();
			config.setIgnoreDefaultExcludes(false);
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(getDlA01(), config);
			renderJson(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getQx() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			StringBuilder gridJson = new StringBuilder("{\"result\":0");
			gridJson.append(",\"qx\":[");
			String qxStr = "";
			if(isSuperUser()){
				qxStr += "100100,200100,200200,200300,300100,300200,300300,300400,300500,300600,300700,300800,300900,400100,500100,600100,700100,700200,800100,900100";
			}else{
				Object obj = getDlA01();
				if(obj != null){
					A01 a01 =  (A01) obj;
					String tempStr = a01.getA01qx();
					if(tempStr != null && !"".equals(tempStr)){
						String[] tempStrs = tempStr.split("@");
						for(String s:tempStrs){
							if(s != null && !"".equals(s)){
								int i = Integer.parseInt(s);
								qxStr += "," + i;
							}
						}
						if(qxStr.length()>0){
							qxStr = qxStr.substring(1);
						}
					}
				}
			}
			gridJson.append(qxStr + "]");
			gridJson.append("}");
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String dengLu() throws ServletException, IOException {
		if(!existsUser()){
			return "login";
		}
		return "success";
	}  
	
	public String checkLogin() throws ServletException, IOException {
		try {
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			HttpSession session = request.getSession();
			A01 a01 = a01ServiceInter.checkLogin(jsonObj.optString("dlm"), jsonObj.optString("mm"));
			StringBuffer gridJson;
			if(a01 != null){
				session.setAttribute("a01", a01);			
				gridJson = new StringBuffer("{\"result\":0}");
			}else{
				gridJson = new StringBuffer("{\"result\":1,\"msg\":\"用户名或密码错误\"}");
			}
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String addA01() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			StringBuffer gridJson = new StringBuffer("{\"result\":0}");
			List list = a01ServiceInter.getSqlResult("select 1 from a01 where bh='" + jsonObj.optString("bh") 
					+ "' or mc='" + jsonObj.optString("mc") + "'");
			if(!list.isEmpty()){
				gridJson = new StringBuffer("{\"result\":-1,\"msg\":\"该编号或姓名已存在\"}");
			}else{
				A01 a01 = new A01();
				a01.setMc(jsonObj.optString("mc"));
                                a01.setDm(jsonObj.optString("dm"));
				a01.setBh(jsonObj.optString("bh"));
				a01.setA0111(jsonObj.optString("a0111"));
				a01.setA0105(jsonObj.optString("a0105"));
				a01.setPassword(jsonObj.optString("password"));
				a01ServiceInter.saveObj(a01);
				DataUtil.setA01Updates(2,true);
                                DataUtil.getA01sFromDb();
			}
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateA01() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			StringBuffer gridJson = new StringBuffer("{\"result\":0}");
			List list = a01ServiceInter.getSqlResult("select 1 from a01 where id != "+jsonObj.optInt("id")+" and (bh='" + jsonObj.optString("bh") 
					+ "' or mc='" + jsonObj.optString("mc") + "')");
			if(!list.isEmpty()){
				gridJson = new StringBuffer("{\"result\":-1,\"msg\":\"该编号或姓名已存在\"}");
			}else{
				A01 a01 = (A01) a01ServiceInter.findById(A01.class, jsonObj.optInt("id"));
				a01.setMc(jsonObj.optString("mc"));
                                a01.setDm(jsonObj.optString("dm"));
				a01.setBh(jsonObj.optString("bh"));
				a01.setA0111(jsonObj.optString("a0111"));
				a01.setA0105(jsonObj.optString("a0105"));
				a01ServiceInter.update(a01);
				DataUtil.setA01Updates(2,true);
                                DataUtil.getA01sFromDb();
			}
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String deleteA01() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			StringBuffer gridJson = new StringBuffer("{\"result\":0}");
			a01ServiceInter.deleteA01(jsonObj.optInt("id"));
			DataUtil.setA01Updates(2,true);
                        DataUtil.getA01sFromDb();
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String changePassword() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			A01 a01 = getDlA01();
			StringBuffer gridJson = new StringBuffer("{\"result\":0}");
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			if(!a01.getPassword().equals(jsonObj.optString("ymm"))){
				gridJson = new StringBuffer("{\"result\":-1,\"msg\":\"原密码不匹配，不能修改密码！\"}");;
			}
			
			String sql = "update a01 set password='" + jsonObj.optString("xmm") + "' where id="+a01.getId();
			a01ServiceInter.excuteSql(sql);
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String changeQuanXian() throws ServletException, IOException {
		try {
			if(!existsUser()){
				return "login";
			}
			JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
			StringBuffer gridJson = new StringBuffer("{\"result\":0}");
                        String qxString = jsonObj.optString("a01qx")==null? "":jsonObj.optString("a01qx");
			String sql = "update a01 set a01qx='" +  qxString + "' where id=" + jsonObj.optInt("id",-1);
			a01ServiceInter.excuteSql(sql);
			renderJson(gridJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}