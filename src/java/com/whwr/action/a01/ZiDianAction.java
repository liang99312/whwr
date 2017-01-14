/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.a01;

import com.whwr.action.BaseAction;
import com.whwr.domain.QiYeZiDian;
import com.whwr.domain.ZiDianLeiBie;
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
public class ZiDianAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goZiDianLeiBie() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getZdlbs() throws ServletException, IOException {
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
            result = commServiceImpl.getPages("from ZiDianLeiBie e" + whereSql, pageNow);
            List<ZiDianLeiBie> eList = commServiceImpl.getPageList("from ZiDianLeiBie e" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (ZiDianLeiBie e : eList) {
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
    
    public String cxZdlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            List<ZiDianLeiBie> eList = commServiceImpl.getResult("from ZiDianLeiBie e", null);
            JSONArray array = new JSONArray();
            for (ZiDianLeiBie e : eList) {
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

    public String addZdlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from ZiDianLeiBie where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                ZiDianLeiBie e = new ZiDianLeiBie();
                e.setMc(jsonObj.optString("mc"));
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

    public String updateZdlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from ZiDianLeiBie where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                ZiDianLeiBie e = (ZiDianLeiBie) commServiceImpl.findById(ZiDianLeiBie.class, jsonObj.optInt("id"));
                e.setMc(jsonObj.optString("mc"));
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

    public String deleteZdlb() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("ZiDianLeiBie", jsonObj.optInt("id"));
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
    
    
    public String goQiYeZiDian() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getQyzds() throws ServletException, IOException {
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
            if(jsonObj.optInt("zdlb",-1) == -1){
                result.put("result", -1);
                result.put("msg", "请按字典类别查询字典信息");
            }else{
                whereSql += " and e.zdlb_id = " + jsonObj.optInt("zdlb",-1);
                result = commServiceImpl.getPages("from QiYeZiDian e where 1=1" + whereSql, pageNow);
                //List<QiYeZiDian> eList = commServiceImpl.getPageList("from QiYeZiDian e" + whereSql, null, pageNow, pageSize);
                List list = commServiceImpl.getSessionFactory().getCurrentSession().createSQLQuery("select {e.*},z.mc as zdlbmc from QiYeZiDian e left join ZiDianLeiBie z on e.zdlb_id=z.id " + whereSql)
                        .addEntity("e",QiYeZiDian.class).addScalar("zdlbmc",StringType.class.newInstance()).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
                JSONArray array = new JSONArray();
                for (Object obj : list) {
                    Object[] objs = (Object[]) obj;
                    QiYeZiDian e = (QiYeZiDian) objs[0];
                    Map<String, Object> map = new HashMap();
                    map.put("id", e.getId());
                    map.put("mc", e.getMc());
                    map.put("bz", e.getBz());
                    map.put("dm", e.getDm());
                    map.put("zdlb_id", e.getZdlb_id());
                    map.put("zdlb_mc", objs[1] == null? "":objs[1].toString());
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

    public String addQyzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from QiYeZiDian where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                QiYeZiDian e = new QiYeZiDian();
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setZdlb_id(jsonObj.getInt("zdlb_id"));
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

    public String updateQyzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from QiYeZiDian where id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该类别名称已存在");
            } else {
                QiYeZiDian e = (QiYeZiDian) commServiceImpl.findById(QiYeZiDian.class, jsonObj.optInt("id"));
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

    public String deleteQyzd() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            commServiceImpl.deleteObjById("QiYeZiDian", jsonObj.optInt("id"));
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
