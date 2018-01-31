/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.ck;

import com.whwr.action.BaseAction;
import com.whwr.domain.ck.CkKc;
import com.whwr.domain.ck.CkKcMx;
import com.whwr.domain.ck.CkLld;
import com.whwr.domain.ck.CkLldMx;
import com.whwr.service.inter.CommServiceInter;
import com.whwr.util.DateUtil;
import com.whwr.util.LshUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author liangxr01
 */
@Controller
public class LingLiaoDanAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goLingLiaoDan() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getLingLiaoDans() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = getJsonObject();
            int pageNow = jsonObj.optInt("yx", 1);
            int pageSize = 20;
            response.setCharacterEncoding("utf-8");
            String whereSql = "";
            whereSql += " cangku_id=" + jsonObj.optInt("cangku_id", 0);
            if (jsonObj.optString("wzmc") != null && !"".equals(jsonObj.optString("wzmc"))) {
                whereSql += " and e.lld_id in (select lld_id from CkLldMx where m_wzmc like '" + jsonObj.optString("wzmc") + "%')";
            }
            if (jsonObj.optString("qi_date") != null && !"".equals(jsonObj.optString("qi_date"))) {
                whereSql += " and e.l_date>='" + jsonObj.optString("qi_date") + "'";
            }
            if (jsonObj.optString("zhi_date") != null && !"".equals(jsonObj.optString("zhi_date"))) {
                whereSql += " and e.l_date<='" + jsonObj.optString("zhi_date") + "'";
            }
            if (jsonObj.optString("state") != null && !"".equals(jsonObj.optString("state"))) {
                whereSql += " and e.l_state='" + jsonObj.optString("state") + "'";
            }
            result = commServiceImpl.getPages("from CkLld e where" + whereSql, pageNow);
            List<CkLld> eList = commServiceImpl.getPageList("from CkLld e where" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (CkLld e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("cangku_id", e.getCangku_id());
                map.put("cangku_mc", e.getCangku_mc());
                map.put("l_zsl", e.getL_zsl());
                map.put("l_zje", e.getL_zje());
                map.put("l_jhr", e.getL_jhr());
                map.put("l_spr", e.getL_spr());
                map.put("l_date", DateUtil.DateToStr(e.getL_date()));
                map.put("l_state", e.getL_state());
                map.put("l_bz", e.getL_bz());
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

    public String getLingLiaoDanById() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            response.setCharacterEncoding("utf-8");
            JSONObject jsonObj = getJsonObject();
            int id = jsonObj.optInt("id", -1);
            if (id == -1) {
                result.clear();
                result.put("result", -1);
                result.put("msg", "找不到入库单信息");
            } else {
                Object obj = commServiceImpl.findById(CkLld.class, id);
                if (obj == null) {
                    result.clear();
                    result.put("result", -1);
                    result.put("msg", "找不到入库单信息");
                } else {
                    List<CkLldMx> eList = commServiceImpl.getResult("from CkLldMx where lld_id=" + id, null);
                    JSONArray mxArray = new JSONArray();
                    for (Object o : eList) {
                        CkLldMx e = (CkLldMx) o;
                        Map<String, Object> map = new HashMap();
                        map.put("id", e.getId());
                        map.put("cangku_id", e.getCangku_id());
                        map.put("cangku_mc", e.getCangku_mc());
                        map.put("m_bz", e.getM_bz());
                        map.put("m_dj", e.getM_dj());
                        map.put("m_dw", e.getM_dw());
                        map.put("m_gxys", e.getM_gxys());
                        map.put("m_gys", e.getM_gys());
                        map.put("m_gys_id", e.getM_gys_id());
                        map.put("m_pp", e.getM_pp());
                        map.put("m_cksl", e.getM_cksl());
                        map.put("m_scc", e.getM_scc());
                        map.put("m_wzmc", e.getM_wzmc());
                        map.put("m_xhgg", e.getM_xhgg());
                        map.put("lld_id", e.getLld_id());
                        map.put("wzzd_id", e.getWzzd_id());
                        map.put("m_mx", e.getM_mx());
                        mxArray.add(map);
                    }
                    CkLld lld = (CkLld) obj;
                    result.put("id", lld.getId());
                    result.put("cangku_id", lld.getCangku_id());
                    result.put("cangku_mc", lld.getCangku_mc());
                    result.put("l_zsl", lld.getL_zsl());
                    result.put("l_zje", lld.getL_zje());
                    result.put("l_jhr", lld.getL_jhr());
                    result.put("l_spr", lld.getL_spr());
                    result.put("l_date", DateUtil.DateToStr(lld.getL_date()));
                    result.put("l_state", lld.getL_state());
                    result.put("l_bz", lld.getL_bz());
                    result.put("mx", mxArray);
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

    public String addLingLiaoDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            CkLld e = new CkLld();
            e.setA01_id(getDlA01().getId());
            e.setLsh(LshUtil.getRcdLsh());
            e.setCangku_id(jsonObj.optInt("cangku_id"));
            e.setCangku_mc(jsonObj.optString("cangku_mc"));
            e.setL_bz(jsonObj.optString("l_bz", ""));
            e.setL_date(new Date());
            e.setL_jhr(getDlA01().getMc());
            e.setL_spr("");
            e.setL_state("未出库");
            e.setL_zje(jsonObj.optDouble("l_zje", 0D));
            e.setL_zsl(jsonObj.optDouble("l_zsl", 0D));
            Integer id = commServiceImpl.saveObj(e);
            JSONArray mx = jsonObj.getJSONArray("mx");
            for (Object obj : mx) {
                JSONObject k = (JSONObject) obj;
                CkLldMx c = new CkLldMx();
                c.setCangku_id(k.optInt("cangku_id"));
                c.setCangku_mc(k.optString("cangku_mc"));
                c.setM_bz(k.optString("m_bz", ""));
                c.setM_dj(k.optDouble("m_dj", 0D));
                c.setM_dw(k.optString("m_dw", ""));
                c.setM_gxys(k.optString("m_gxys", ""));
                c.setM_pp(k.optString("m_pp", ""));
                c.setM_cksl(k.optDouble("m_cksl", 0D));
                c.setM_scc(k.optString("m_scc", ""));
                c.setM_wzmc(k.optString("m_wzmc", ""));
                c.setM_xhgg(k.optString("m_xhgg", ""));
                c.setM_mx(k.optString("m_mx", ""));
                c.setLld_id(id);
                c.setWzzd_id(k.optInt("wzzd_id", 0));
                commServiceImpl.saveObj(c);
            }
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

    public String updateLingLiaoDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            CkLld e = (CkLld) commServiceImpl.findById(CkLld.class, jsonObj.optInt("id"));
            if (e == null) {
                result.clear();
                result.put("result", -1);
                result.put("msg", "找不到该入库单");
            } else {
                e.setA01_id(getDlA01().getId());
                e.setCangku_id(jsonObj.optInt("cangku_id"));
                e.setCangku_mc(jsonObj.optString("cangku_mc"));
                e.setL_bz(jsonObj.optString("l_bz", ""));
                e.setL_date(new Date());
                e.setL_jhr(getDlA01().getMc());
                e.setL_spr("");
                e.setL_state("未入库");
                e.setL_zje(jsonObj.optDouble("l_zje", 0D));
                e.setL_zsl(jsonObj.optDouble("l_zsl", 0D));
                commServiceImpl.update(e);
                commServiceImpl.excuteSql("delete from CkLldMx where lld_id=" + e.getId());
                JSONArray mx = jsonObj.getJSONArray("mx");
                for (Object obj : mx) {
                    JSONObject k = (JSONObject) obj;
                    CkLldMx c = new CkLldMx();
                    c.setCangku_id(k.optInt("cangku_id"));
                    c.setCangku_mc(k.optString("cangku_mc"));
                    c.setM_bz(k.optString("m_bz", ""));
                    c.setM_dj(k.optDouble("m_dj", 0D));
                    c.setM_dw(k.optString("m_dw", ""));
                    c.setM_gxys(k.optString("m_gxys", ""));
                    c.setM_pp(k.optString("m_pp", ""));
                    c.setM_cksl(k.optDouble("m_cksl", 0D));
                    c.setM_scc(k.optString("m_scc", ""));
                    c.setM_wzmc(k.optString("m_wzmc", ""));
                    c.setM_xhgg(k.optString("m_xhgg", ""));
                    c.setM_mx(k.optString("m_mx", ""));
                    c.setLld_id(e.getId());
                    c.setWzzd_id(k.optInt("wzzd_id", 0));
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

    public String deleteLingLiaoDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List<String> sqls = new ArrayList<String>();
            sqls.add("delete from CkLldMx where lld_id=" + jsonObj.optInt("id"));
            sqls.add("delete from CkLld where id=" + jsonObj.optInt("id"));
            commServiceImpl.excuteSqls(sqls);
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
    
    public String banLiLingLiaoDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            Integer id = jsonObj.optInt("id");
            CkLld e = (CkLld) commServiceImpl.findById(CkLld.class, id);
            e.setL_state("已出库");
            e.setL_spr(getDlA01().getMc());
            commServiceImpl.update(e);
            List<CkLldMx> mxList = commServiceImpl.getResult("from CkLldMx where lld_id='"+id+"'", null);
            List<CkKc> kcList = commServiceImpl.getResult("from CkKc where id in(select kucun_id from CkLldMx where lld_id='"+id+"')", null);
            HashMap<Integer,CkKc> kcMap = new HashMap<Integer,CkKc>();
            for (CkKc kc : kcList) {
                kcMap.put(kc.getId(),kc);
            }
            for(CkLldMx m:mxList){
                CkKc c = kcMap.get(m.getKucun_id());
                if(c != null){
                    if(c.getK_syl() < m.getM_cksl()){
                        throw new Exception(c.getK_wzmc() + "库存量不足！");
                    }
                    c.setK_cksl(c.getK_cksl() + m.getM_cksl());
                    commServiceImpl.update(c);
                    if(m.getM_mx() != null && !"".equals(m.getM_mx())){
                        JSONArray array = JSONArray.fromObject(m.getM_mx());
                        List<String> sqlList = new ArrayList<String>();
                        for(Object obj : array){
                            JSONObject j = (JSONObject) obj;
                            int xh = j.optInt("xh");
                            String sql = "update CkKcMx set zt = 1 where kucun_id=" + c.getId() + " and xh=" + xh;
                            sqlList.add(sql);
                        }
                        commServiceImpl.excuteSqls(sqlList);
                    }
                }
            }
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
