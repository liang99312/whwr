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
import com.whwr.domain.ck.CkRkd;
import com.whwr.domain.ck.CkRkdMx;
import com.whwr.service.inter.CommServiceInter;
import com.whwr.util.DateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class RuKuDanAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goRuKuDan() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getRuKuDans() throws ServletException, IOException {
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
                whereSql += " and e.rkd_id in (select rkd_id from CkRkdMx where m_wzmc like '" + jsonObj.optString("wzmc") + "%')";
            }
            if (jsonObj.optString("qi_date") != null && !"".equals(jsonObj.optString("qi_date"))) {
                whereSql += " and e.r_date>='" + jsonObj.optString("qi_date") + "'";
            }
            if (jsonObj.optString("zhi_date") != null && !"".equals(jsonObj.optString("zhi_date"))) {
                whereSql += " and e.r_date<='" + jsonObj.optString("zhi_date") + "'";
            }
            if (jsonObj.optString("state") != null && !"".equals(jsonObj.optString("state"))) {
                whereSql += " and e.r_state='" + jsonObj.optString("state") + "'";
            }
            result = commServiceImpl.getPages("from CkRkd e where" + whereSql, pageNow);
            List<CkRkd> eList = commServiceImpl.getPageList("from CkRkd e where" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (CkRkd e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("cangku_id", e.getCangku_id());
                map.put("cangku_mc", e.getCangku_mc());
                map.put("r_ly", e.getR_ly());
                map.put("r_gys_id", e.getR_gys_id());
                map.put("r_gys", e.getR_gys());
                map.put("r_dh", e.getR_dh());
                map.put("r_zsl", e.getR_zsl());
                map.put("r_zje", e.getR_zje());
                map.put("r_jhr", e.getR_jhr());
                map.put("r_spr", e.getR_spr());
                map.put("r_date", DateUtil.DateToStr(e.getR_date()));
                map.put("r_state", e.getR_state());
                map.put("r_bz", e.getR_bz());
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

    public String getRuKuDanById() throws ServletException, IOException {
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
                Object obj = commServiceImpl.findById(CkRkd.class, id);
                if (obj == null) {
                    result.clear();
                    result.put("result", -1);
                    result.put("msg", "找不到入库单信息");
                } else {
                    List<CkRkdMx> eList = commServiceImpl.getResult("from CkRkdMx where rkd_id=" + id, null);
                    JSONArray mxArray = new JSONArray();
                    for (Object o : eList) {
                        CkRkdMx e = (CkRkdMx) o;
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
                        map.put("m_rksl", e.getM_rksl());
                        map.put("m_scc", e.getM_scc());
                        map.put("m_wzmc", e.getM_wzmc());
                        map.put("m_xhgg", e.getM_xhgg());
                        map.put("rkd_id", e.getRkd_id());
                        map.put("wzzd_id", e.getWzzd_id());
                        mxArray.add(map);
                    }
                    CkRkd rkd = (CkRkd) obj;
                    result.put("id", rkd.getId());
                    result.put("cangku_id", rkd.getCangku_id());
                    result.put("cangku_mc", rkd.getCangku_mc());
                    result.put("r_ly", rkd.getR_ly());
                    result.put("r_gys_id", rkd.getR_gys_id());
                    result.put("r_gys", rkd.getR_gys());
                    result.put("r_dh", rkd.getR_dh());
                    result.put("r_zsl", rkd.getR_zsl());
                    result.put("r_zje", rkd.getR_zje());
                    result.put("r_jhr", rkd.getR_jhr());
                    result.put("r_spr", rkd.getR_spr());
                    result.put("r_date", DateUtil.DateToStr(rkd.getR_date()));
                    result.put("r_state", rkd.getR_state());
                    result.put("r_bz", rkd.getR_bz());
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

    public String addRuKuDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            CkRkd e = new CkRkd();
            e.setA01_id(getDlA01().getId());
            e.setCangku_id(jsonObj.optInt("cangku_id"));
            e.setCangku_mc(jsonObj.optString("cangku_mc"));
            e.setR_bz(jsonObj.optString("r_bz", ""));
            e.setR_date(new Date());
            e.setR_dh(jsonObj.optString("r_dh", ""));
            e.setR_gys(jsonObj.optString("r_gys", ""));
            e.setR_gys_id(jsonObj.optInt("r_gys_id", -1));
            e.setR_jhr(getDlA01().getMc());
            e.setR_ly(jsonObj.optString("r_ly", ""));
            e.setR_spr("");
            e.setR_state("未入库");
            e.setR_zje(jsonObj.optDouble("r_zje", 0D));
            e.setR_zsl(jsonObj.optDouble("r_zsl", 0D));
            Integer id = commServiceImpl.saveObj(e);
            JSONArray mx = jsonObj.getJSONArray("mx");
            for (Object obj : mx) {
                JSONObject k = (JSONObject) obj;
                CkRkdMx c = new CkRkdMx();
                c.setCangku_id(k.optInt("cangku_id"));
                c.setCangku_mc(k.optString("cangku_mc"));
                c.setM_bz(k.optString("m_bz", ""));
                c.setM_dj(k.optDouble("m_dj", 0D));
                c.setM_dw(k.optString("m_dw", ""));
                c.setM_gxys(k.optString("m_gxys", ""));
                c.setM_gys(e.getR_gys());
                c.setM_gys_id(e.getR_gys_id());
                c.setM_pp(k.optString("m_pp", ""));
                c.setM_rksl(k.optDouble("m_rksl", 0D));
                c.setM_scc(k.optString("m_scc", ""));
                c.setM_wzmc(k.optString("m_wzmc", ""));
                c.setM_xhgg(k.optString("m_xhgg", ""));
                c.setRkd_id(id);
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

    public String updateRuKuDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            CkRkd e = (CkRkd) commServiceImpl.findById(CkRkd.class, jsonObj.optInt("id"));
            if (e == null) {
                result.clear();
                result.put("result", -1);
                result.put("msg", "找不到该入库单");
            } else {
                e.setA01_id(getDlA01().getId());
                e.setCangku_id(jsonObj.optInt("cangku_id"));
                e.setCangku_mc(jsonObj.optString("cangku_mc"));
                e.setR_bz(jsonObj.optString("r_bz", ""));
                e.setR_date(new Date());
                e.setR_dh(jsonObj.optString("r_dh", ""));
                e.setR_gys(jsonObj.optString("r_gys", ""));
                e.setR_gys_id(jsonObj.optInt("r_gys_id", -1));
                e.setR_jhr(getDlA01().getMc());
                e.setR_ly(jsonObj.optString("r_ly", ""));
                e.setR_spr("");
                e.setR_state("未入库");
                e.setR_zje(jsonObj.optDouble("r_zje", 0D));
                e.setR_zsl(jsonObj.optDouble("r_zsl", 0D));
                commServiceImpl.update(e);
                commServiceImpl.excuteSql("delete from CkRkdMx where rkd_id=" + e.getId());
                JSONArray mx = jsonObj.getJSONArray("mx");
                for (Object obj : mx) {
                    JSONObject k = (JSONObject) obj;
                    CkRkdMx c = new CkRkdMx();
                    c.setCangku_id(k.optInt("cangku_id"));
                    c.setCangku_mc(k.optString("cangku_mc"));
                    c.setM_bz(k.optString("m_bz", ""));
                    c.setM_dj(k.optDouble("m_dj", 0D));
                    c.setM_dw(k.optString("m_dw", ""));
                    c.setM_gxys(k.optString("m_gxys", ""));
                    c.setM_gys(e.getR_gys());
                    c.setM_gys_id(e.getR_gys_id());
                    c.setM_pp(k.optString("m_pp", ""));
                    c.setM_rksl(k.optDouble("m_rksl", 0D));
                    c.setM_scc(k.optString("m_scc", ""));
                    c.setM_wzmc(k.optString("m_wzmc", ""));
                    c.setM_xhgg(k.optString("m_xhgg", ""));
                    c.setRkd_id(e.getId());
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

    public String deleteRuKuDan() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List<String> sqls = new ArrayList<String>();
            sqls.add("delete from CkRkd where id=" + jsonObj.optInt("id"));
            sqls.add("delete from CkRkdMx where rkd_id=" + jsonObj.optInt("id"));
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
}
