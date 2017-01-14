/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.a01;

import com.whwr.action.BaseAction;
import com.whwr.domain.BuMen;
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
public class BuMenAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goBuMen() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }
    
    public String cxBm() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            List<BuMen> eList = commServiceImpl.getResult("from BuMen e", null);
            JSONArray array = new JSONArray();
            for (BuMen e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
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

    public String getBuMens() throws ServletException, IOException {
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
                whereSql += "or e.mc like '" + jsonObj.optString("mc") + "%' ";
            }
            if (" where (1=0 ".equals(whereSql)) {
                whereSql = "";
            } else {
                whereSql += ")";
            }
            result = commServiceImpl.getPages("from BuMen e" + whereSql, pageNow);
            List<BuMen> eList = commServiceImpl.getPageList("from BuMen e" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (BuMen e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
                map.put("dm", e.getDm());
                map.put("bz", e.getBz());
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

    public String addBuMen() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from bumen where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                BuMen e = new BuMen();
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setBz(jsonObj.optString("bz"));
                commServiceImpl.saveObj(e);
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

    public String updateBuMen() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from bumen where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                BuMen e = (BuMen) commServiceImpl.findById(BuMen.class, jsonObj.optInt("id"));
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setBz(jsonObj.optString("bz"));
                commServiceImpl.update(e);
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

    public String deleteBuMen() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("BuMen", jsonObj.optInt("id"));
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
