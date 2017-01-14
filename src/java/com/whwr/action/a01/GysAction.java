/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.a01;

import com.whwr.action.BaseAction;
import com.whwr.domain.GongYingShang;
import com.whwr.service.inter.CommServiceInter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jane
 */
@Controller
public class GysAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goGongYingShang() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }
    
    public String cxGongYingShang() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            response.setCharacterEncoding("utf-8");
            List<GongYingShang> gysList = commServiceImpl.getResult("from GongYingShang gys",null);
            JSONArray array = new JSONArray();
            for (GongYingShang gys : gysList) {
                Map<String, Object> map = new HashMap();
                map.put("id", gys.getId());
                map.put("mc", gys.getMc());
                map.put("dm", gys.getDm());
                array.add(map);
            }
            result.put("sz", array);
        } catch (Exception e) {
            result.clear();
            result.put("result", -1);
            result.put("msg", e.getMessage());
        } finally {
            renderJson(result.toString());
        }
        return null;
    }

    public String getGongYingShangs() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = getJsonObject();
            int pageNow = jsonObj.optInt("yx", 1);
            int pageSize = 20;
            response.setCharacterEncoding("utf-8");
            String whereSql = " where (1=0 ";
            if (jsonObj.optString("mc") != null && !"".equals(jsonObj.optString("mc"))) {
                whereSql += "or gys.mc like '" + jsonObj.optString("mc") + "%' ";
            }
            if (" where (1=0 ".equals(whereSql)) {
                whereSql = "";
            } else {
                whereSql += ")";
            }
            result = commServiceImpl.getPages("from GongYingShang gys" + whereSql, pageNow);
            List<GongYingShang> gysList = commServiceImpl.getPageList("from GongYingShang gys" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (GongYingShang gys : gysList) {
                Map<String, Object> map = new HashMap();
                map.put("id", gys.getId());
                map.put("mc", gys.getMc());
                map.put("dm", gys.getDm());
                map.put("dz", gys.getDz());
                map.put("lxr", gys.getLxr());
                map.put("lxdh", gys.getLxdh());
                map.put("bz", gys.getBz());
                array.add(map);
            }
            result.put("sz", array);
        } catch (Exception e) {
            result.clear();
            result.put("result", -1);
            result.put("msg", e.getMessage());
        } finally {
            renderJson(result.toString());
        }
        return null;
    }

    public String addGongYingShang() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from gongyingshang where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该供应商名称已存在");
            } else {
                GongYingShang gys = new GongYingShang();
                gys.setMc(jsonObj.optString("mc"));
                gys.setDm(jsonObj.optString("dm"));
                gys.setDz(jsonObj.optString("dz"));
                gys.setLxr(jsonObj.optString("lxr"));
                gys.setLxdh(jsonObj.optString("lxdh"));
                gys.setBz(jsonObj.optString("bz"));
                commServiceImpl.saveObj(gys);
                result.put("result", 0);
            }
            renderJson(result.toString());
        } catch (Exception e) {
            result.clear();
            result.put("result", -1);
            result.put("msg", e.getMessage());
        } finally {
            renderJson(result.toString());
        }
        return null;
    }

    public String updateGongYingShang() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from gongyingshang where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该供应商名称已存在");
            } else {
                GongYingShang gys = (GongYingShang) commServiceImpl.findById(GongYingShang.class, jsonObj.optInt("id"));
                gys.setMc(jsonObj.optString("mc"));
                gys.setDm(jsonObj.optString("dm"));
                gys.setDz(jsonObj.optString("dz"));
                gys.setLxr(jsonObj.optString("lxr"));
                gys.setLxdh(jsonObj.optString("lxdh"));
                gys.setBz(jsonObj.optString("bz"));
                commServiceImpl.update(gys);
                result.put("result", 0);
            }
        } catch (Exception e) {
            result.clear();
            result.put("result", -1);
            result.put("msg", e.getMessage());
        } finally {
            renderJson(result.toString());
        }
        return null;
    }

    public String deleteGongYingShang() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("GongYingShang", jsonObj.optInt("id"));
            result.put("result", 0);
        } catch (Exception e) {
            result.clear();
            result.put("result", -1);
            result.put("msg", e.getMessage());
        } finally {
            renderJson(result.toString());
        }
        return null;
    }
}
