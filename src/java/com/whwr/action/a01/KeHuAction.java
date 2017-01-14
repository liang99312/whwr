/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.a01;

import com.whwr.action.BaseAction;
import com.whwr.domain.KeHu;
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
public class KeHuAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goKeHu() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getKeHus() throws ServletException, IOException {
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
                whereSql += "or kh.mc like '" + jsonObj.optString("mc") + "%' ";
            }
            if (" where (1=0 ".equals(whereSql)) {
                whereSql = "";
            } else {
                whereSql += ")";
            }
            result = commServiceImpl.getPages("from KeHu kh" + whereSql, pageNow);
            List<KeHu> khList = commServiceImpl.getPageList("from KeHu kh" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (KeHu kh : khList) {
                Map<String, Object> map = new HashMap();
                map.put("id", kh.getId());
                map.put("mc", kh.getMc());
                map.put("dm", kh.getDm());
                map.put("dz", kh.getDz());
                map.put("lxr", kh.getLxr());
                map.put("lxdh", kh.getLxdh());
                map.put("bz", kh.getBz());
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

    public String addKeHu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from kehu where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                KeHu kh = new KeHu();
                kh.setMc(jsonObj.optString("mc"));
                kh.setDm(jsonObj.optString("dm"));
                kh.setDz(jsonObj.optString("dz"));
                kh.setLxr(jsonObj.optString("lxr"));
                kh.setLxdh(jsonObj.optString("lxdh"));
                kh.setBz(jsonObj.optString("bz"));
                commServiceImpl.saveObj(kh);
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

    public String updateKeHu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from kehu where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                KeHu kh = (KeHu) commServiceImpl.findById(KeHu.class, jsonObj.optInt("id"));
                kh.setMc(jsonObj.optString("mc"));
                kh.setDm(jsonObj.optString("dm"));
                kh.setDz(jsonObj.optString("dz"));
                kh.setLxr(jsonObj.optString("lxr"));
                kh.setLxdh(jsonObj.optString("lxdh"));
                kh.setBz(jsonObj.optString("bz"));
                commServiceImpl.update(kh);
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

    public String deleteKeHu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("KeHu", jsonObj.optInt("id"));
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
