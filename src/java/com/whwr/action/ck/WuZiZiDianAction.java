/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.ck;

import com.whwr.action.BaseAction;
import com.whwr.domain.ck.WuZiLeiBie;
import com.whwr.domain.ck.WuZiXhgg;
import com.whwr.domain.ck.WuZiZiDian;
import com.whwr.service.inter.CommServiceInter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jane
 */
@Controller
public class WuZiZiDianAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }
    

    public String goWuZiLeiBie() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getWzlbs() throws ServletException, IOException {
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
            result = commServiceImpl.getPages("from WuZiLeiBie e" + whereSql, pageNow);
            List<WuZiLeiBie> eList = commServiceImpl.getPageList("from WuZiLeiBie e" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (WuZiLeiBie e : eList) {
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
    
    public String cxWzlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            List<WuZiLeiBie> eList = commServiceImpl.getResult("from WuZiLeiBie e", null);
            JSONArray array = new JSONArray();
            for (WuZiLeiBie e : eList) {
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

    public String addWzlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiLeiBie where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                WuZiLeiBie e = new WuZiLeiBie();
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

    public String updateWzlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiLeiBie where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                WuZiLeiBie e = (WuZiLeiBie) commServiceImpl.findById(WuZiLeiBie.class, jsonObj.optInt("id"));
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

    public String deleteWzlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("WuZiLeiBie", jsonObj.optInt("id"));
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
    
    
    public String goWuZiZiDian() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }
    
    public String cxWzzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            List<WuZiLeiBie> eList = commServiceImpl.getResult("from WuZiZiDian e", null);
            JSONArray array = new JSONArray();
            for (WuZiLeiBie e : eList) {
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

    public String getWzzds() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = getJsonObject();
            int pageNow = jsonObj.optInt("yx", 1);
            int pageSize = 20;
            response.setCharacterEncoding("utf-8");
            String whereSql = " and (1=0 ";
            if (jsonObj.optString("mc") != null && !"".equals(jsonObj.optString("mc"))) {
                whereSql += "or e.mc like '" + jsonObj.optString("mc") + "%' ";
            }
            
            if (" and (1=0 ".equals(whereSql)) {
                whereSql = "";
            } else {
                whereSql += ")";
            }
            if(jsonObj.optInt("wzlb",-1) == -1){
                result.put("result", -1);
                result.put("msg", "请按物资类别查询物资字典信息");
            }else{
                whereSql += " and e.wzlb_id = " + jsonObj.optInt("wzlb",-1);
                result = commServiceImpl.getPages("from WuZiZiDian e where 1=1 " + whereSql, pageNow);
                List list = commServiceImpl.getSessionFactory().getCurrentSession().createSQLQuery("select {e.*},z.mc as wzlbmc from WuZiZiDian e left join WuZiLeiBie z on e.wzlb_id=z.id where 1=1 " + whereSql)
                        .addEntity("e",WuZiZiDian.class).addScalar("wzlbmc",StringType.class.newInstance()).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
                JSONArray array = new JSONArray();
                for (Object obj : list) {
                    Object[] objs = (Object[]) obj;
                    WuZiZiDian e = (WuZiZiDian) objs[0];
                    Map<String, Object> map = new HashMap();
                    map.put("id", e.getId());
                    map.put("mc", e.getMc());
                    map.put("bz", e.getBz());
                    map.put("dm", e.getDm());
                    map.put("bm", e.getBm());
                    map.put("dw", e.getDw());
                    map.put("gxys", e.getGxys());
                    map.put("wzlb_id", e.getWzlb_id());
                    map.put("wzlb_mc", objs[1] == null? "":objs[1].toString());
                    array.add(map);
                }
                result.put("sz", array);
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
    
    public String addWzzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiZiDian where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                WuZiZiDian e = new WuZiZiDian();
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setWzlb_id(jsonObj.getInt("wzlb_id"));
                e.setBz(jsonObj.optString("bz"));
                e.setBm(jsonObj.optString("bm"));
                e.setDw(jsonObj.optString("dw"));
                e.setGxys(jsonObj.optString("gxys"));
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

    public String addWzzdAndXhgg() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiZiDian where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                WuZiZiDian e = new WuZiZiDian();
                e.setMc(jsonObj.optString("wzzd_mc"));
                e.setWzlb_id(jsonObj.getInt("wzlb_id"));
                e.setDw(jsonObj.optString("wzzd_dw"));
                e.setBz("");
                e.setBm("");
                e.setDm("");
                Integer i = commServiceImpl.saveObj(e);
                WuZiXhgg w = new WuZiXhgg();
                w.setMc(jsonObj.optString("xhgg_mc"));
                w.setBz("");
                w.setDm("");
                w.setJb("0");
                w.setSl(0D);
                w.setWzzd_id(i);
                commServiceImpl.saveObj(w);
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

    public String updateWzzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiZiDian where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                WuZiZiDian e = (WuZiZiDian) commServiceImpl.findById(WuZiZiDian.class, jsonObj.optInt("id"));
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setBz(jsonObj.optString("bz"));
                e.setBm(jsonObj.optString("bm"));
                e.setDw(jsonObj.optString("dw"));
                e.setGxys(jsonObj.optString("gxys"));
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

    public String deleteWzzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("WuZiZiDian", jsonObj.optInt("id"));
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
    
    
    public String cxWzzdXhgg() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            if(jsonObj.optInt("wzzd_id",-1) == -1){
                result.put("result", -1);
                result.put("msg", "请按物资字典查询型号规格信息");
            }else{
                List<WuZiXhgg> eList = commServiceImpl.getResult("from WuZiXhgg e where e.wzzd_id="+jsonObj.optInt("wzzd_id",-1) , null);
                JSONArray array = new JSONArray();
                for (WuZiXhgg e : eList) {
                    Map<String, Object> map = new HashMap();
                    map.put("id", e.getId());
                    map.put("mc", e.getMc());
                    map.put("dm", e.getDm());
                    map.put("sl", e.getSl());
                    map.put("jb", e.getJb());
                    map.put("bz", e.getBz());
                    array.add(map);
                }
                result.put("sz", array);
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

    public String addWzzdXhgg() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiXhgg where wzzd_id="+jsonObj.optInt("wzzd_id")+" and id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该型号规格名称名称已存在");
            } else {
                WuZiXhgg e = new WuZiXhgg();
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm",""));
                e.setSl(jsonObj.optDouble("sl", 0.0D));
                e.setJb(jsonObj.optString("jb","0"));
                e.setBz(jsonObj.optString("bz",""));
                e.setWzzd_id(jsonObj.getInt("wzzd_id"));
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

    public String updateWzzdXhgg() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from WuZiXhgg where wzzd_id="+jsonObj.optInt("wzzd_id")+" and id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该型号规格名称名称已存在");
            } else {
                WuZiXhgg e = (WuZiXhgg) commServiceImpl.findById(WuZiXhgg.class, jsonObj.optInt("id"));
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setSl(jsonObj.optDouble("sl", 0.0D));
                e.setJb(jsonObj.optString("jb","0"));
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

    public String deleteWzzdXhgg() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("WuZiXhgg", jsonObj.optInt("id"));
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
