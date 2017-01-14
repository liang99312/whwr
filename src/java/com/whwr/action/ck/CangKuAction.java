/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.ck;

import com.whwr.action.BaseAction;
import com.whwr.domain.A01;
import com.whwr.domain.ck.CangKu;
import com.whwr.domain.ck.CkA01;
import com.whwr.domain.ck.CkKw;
import com.whwr.service.inter.CommServiceInter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author liangxr01
 */
@Controller
public class CangKuAction extends BaseAction {

	@Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goCangKu() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }
    
    public String cxKeGuanCangKu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            A01 a01 = getDlA01();
            String whersSql = "";
            if(a01.getId() > 0){
                whersSql = " and id in(select cangku_id from CkA01 where a01_id="+a01.getId()+")";
            }
            //根据人员查询仓库
            List<CangKu> eList = commServiceImpl.getResult("from CangKu e where e.flag=0"+whersSql, null);
            JSONArray array = new JSONArray();
            for (CangKu e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
                map.put("dm", e.getDm());
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
    
    public String cxCk() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            A01 a01 = getDlA01();
            String whersSql = "";
            if(a01.getId() > 0){
                whersSql = " and id in(select cangku_id from CkA01 where a01_id="+a01.getId()+")";
            }
            //根据人员查询仓库
            List<CangKu> eList = commServiceImpl.getResult("from CangKu e where e.flag=0"+whersSql, null);
            JSONArray array = new JSONArray();
            for (CangKu e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
                map.put("dm", e.getDm());
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
    
    public String cxKgCk() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            //根据人员查询仓库
            List<CangKu> eList = commServiceImpl.getResult("from CangKu e where e.flag=0", null);
            JSONArray array = new JSONArray();
            for (CangKu e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
                map.put("dm", e.getDm());
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

    public String getCangKus() throws ServletException, IOException {
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
            result = commServiceImpl.getPages("from CangKu e where flag=0" + whereSql, pageNow);
            List<CangKu> eList = commServiceImpl.getPageList("from CangKu e where flag=0" + whereSql, null, pageNow, pageSize);
            String idStr = "-1";
            for (CangKu e : eList) {
                idStr = idStr + "," + e.getId();
            }
            Hashtable<Integer,JSONArray> kwTable = new Hashtable<Integer,JSONArray>();
            List kwList = commServiceImpl.getResult("from CkKw where cangku_id in("+idStr+")", null);
            for(Object obj:kwList){
                CkKw kw = (CkKw) obj;
                Map<String, Object> tempMap = new HashMap();
                tempMap.put("id", kw.getId());
                tempMap.put("mc", kw.getMc());
                tempMap.put("qi", kw.getQi_id());
                tempMap.put("zhi", kw.getZhi_id());
                JSONArray tempArray = new JSONArray();
                if(kwTable.containsKey(kw.getCangku_id())){
                    tempArray = kwTable.get(kw.getCangku_id());
                    tempArray.add(tempMap);
                }else{
                    tempArray.add(tempMap);
                    kwTable.put(kw.getCangku_id(), tempArray);
                }
            }
            Hashtable<Integer,JSONArray> mcTable = new Hashtable<Integer,JSONArray>();
            List mcList = commServiceImpl.getSqlResult("select c.cangku_id,a.id,a.mc from A01 a,CkA01 c where a.id=c.a01_id and c.cangku_id in("+idStr+")");
            for(Object obj:mcList){
                Object[] objs = (Object[]) obj;
                int id = (Integer) objs[0];
                int a01_id = (Integer) objs[1];
                String mc = objs[2].toString();
                Map<String, Object> tempMap = new HashMap();
                tempMap.put("id", a01_id);
                tempMap.put("mc", mc);
                JSONArray tempArray = new JSONArray();
                if(mcTable.containsKey(id)){
                    tempArray = mcTable.get(id);
                    tempArray.add(tempMap);
                }else{
                    tempArray.add(tempMap);
                    mcTable.put(id, tempArray);
                }
            }
            JSONArray array = new JSONArray();
            for (CangKu e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("mc", e.getMc());
                map.put("dm", e.getDm());
                map.put("bz", e.getBz());
                map.put("gly", mcTable.get(e.getId()));
                map.put("kw", kwTable.get(e.getId()));
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
    
    public String getCangKuById() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            response.setCharacterEncoding("utf-8");
            JSONObject jsonObj = getJsonObject();
            int id = jsonObj.optInt("id", -1);
            if(id == -1){
                result.clear();
                result.put("result", -1);
                result.put("msg", "找不到该仓库");
            }else{
                Object obj = commServiceImpl.findById(CangKu.class, id);
                if(obj == null){
                    result.clear();
                    result.put("result", -1);
                    result.put("msg", "找不到该仓库");
                }else{
                    CangKu ck = (CangKu) obj;
                    result.put("id", ck.getId());
                    result.put("mc", ck.getMc());
                    result.put("dm", ck.getDm());
                    result.put("bz", ck.getBz());
                    result.put("result", 0);
                }
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

    public String addCangKu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from CangKu where flag=0 and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                CangKu e = new CangKu();
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setBz(jsonObj.optString("bz"));
                Integer id = commServiceImpl.saveObj(e);
                JSONArray kw = jsonObj.getJSONArray("kw");
                for(Object obj : kw){
                    JSONObject k = (JSONObject) obj;
                    CkKw c = new CkKw();
                    c.setCangku_id(id);
                    c.setMc(k.getString("mc"));
                    c.setQi_id(k.getInt("qi"));
                    c.setZhi_id(k.getInt("zhi"));
                    commServiceImpl.saveObj(c);
                }
                JSONArray gly = jsonObj.getJSONArray("gly");
                for(Object obj : gly){
                    JSONObject g = (JSONObject) obj;
                    CkA01 c = new CkA01();
                    c.setCangku_id(id);
                    c.setA01_id(g.getInt("id"));
                    commServiceImpl.saveObj(c);
                }
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

    public String updateCangKu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List list = commServiceImpl.getSqlResult("select 1 from cangku where flag=0 and id != " + jsonObj.optInt("id") + " and mc='" + jsonObj.optString("mc") + "'");
            if (!list.isEmpty()) {
                result.put("result", -1);
                result.put("msg", "该客户名称已存在");
            } else {
                CangKu e = (CangKu) commServiceImpl.findById(CangKu.class, jsonObj.optInt("id"));
                e.setMc(jsonObj.optString("mc"));
                e.setDm(jsonObj.optString("dm"));
                e.setBz(jsonObj.optString("bz"));
                commServiceImpl.update(e);
                commServiceImpl.excuteSql("delete from CkKw where cangku_id="+e.getId());
                JSONArray kw = jsonObj.getJSONArray("kw");
                for(Object obj : kw){
                    JSONObject k = (JSONObject) obj;
                    CkKw c = new CkKw();
                    c.setCangku_id(e.getId());
                    c.setMc(k.getString("mc"));
                    c.setQi_id(k.getInt("qi"));
                    c.setZhi_id(k.getInt("zhi"));
                    commServiceImpl.saveObj(c);
                }
                commServiceImpl.excuteSql("delete from CkA01 where cangku_id="+e.getId());
                JSONArray gly = jsonObj.getJSONArray("gly");
                for(Object obj : gly){
                    JSONObject g = (JSONObject) obj;
                    CkA01 c = new CkA01();
                    c.setCangku_id(e.getId());
                    c.setA01_id(g.getInt("id"));
                    commServiceImpl.saveObj(c);
                }
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

    public String deleteCangKu() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            String sql = "update CangKu set flag=-1 where id="+jsonObj.optInt("id");
            commServiceImpl.excuteSql(sql);
//            commServiceImpl.deleteObjById("CangKu", jsonObj.optInt("id"));
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
