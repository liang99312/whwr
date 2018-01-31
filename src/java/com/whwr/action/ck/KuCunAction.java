/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.action.ck;

import com.whwr.action.BaseAction;
import com.whwr.domain.ck.CkKc;
import com.whwr.domain.ck.CkKcMx;
import com.whwr.service.inter.CommServiceInter;
import java.io.IOException;
import java.util.ArrayList;
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
public class KuCunAction extends BaseAction {

    @Resource
    private CommServiceInter commServiceImpl;

    public CommServiceInter getCommServiceInter() {
        return commServiceImpl;
    }

    public void setCommServiceInter(CommServiceInter commServiceImpl) {
        this.commServiceImpl = commServiceImpl;
    }

    public String goKuCun() throws ServletException, IOException {
        if (!existsUser()) {
            return "login";
        }
        return "success";
    }

    public String getKuCuns() throws ServletException, IOException {
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
                whereSql += " and e.k_wzmc like '" + jsonObj.optString("wzmc") + "%'";
            }
            if (jsonObj.optString("lsh") != null && !"".equals(jsonObj.optString("lsh"))) {
                whereSql += " and e.lsh like '" + jsonObj.optString("lsh") + "%'";
            }
            if (jsonObj.optString("qi_date") != null && !"".equals(jsonObj.optString("qi_date"))) {
                whereSql += " and e.r_date>='" + jsonObj.optString("qi_date") + "'";
            }
            if (jsonObj.optString("zhi_date") != null && !"".equals(jsonObj.optString("zhi_date"))) {
                whereSql += " and e.r_date<='" + jsonObj.optString("zhi_date") + "'";
            }
            result = commServiceImpl.getPages("from CkKc e where" + whereSql, pageNow);
            List<CkKc> eList = commServiceImpl.getPageList("from CkKc e where" + whereSql, null, pageNow, pageSize);
            JSONArray array = new JSONArray();
            for (CkKc e : eList) {
                Map<String, Object> map = new HashMap();
                map.put("id", e.getId());
                map.put("cangku_id", e.getCangku_id());
                map.put("cangku_mc", e.getCangku_mc());
                map.put("k_bz", e.getK_bz());
                map.put("k_dw", e.getK_dw());
                map.put("k_gxys", e.getK_gxys());
                map.put("k_gys", e.getK_gys());
                map.put("k_gys_id", e.getK_gys_id());
                map.put("k_pp", e.getK_pp());
                map.put("k_rksl", e.getK_rksl());
                map.put("k_scc", e.getK_scc());
                map.put("k_wzmc", e.getK_wzmc());
                map.put("k_xhgg", e.getK_xhgg());
                map.put("rkdmx_id", e.getRkdmx_id());
                map.put("wzzd_id", e.getWzzd_id());
                map.put("k_rkjg", e.getK_rkjg());
                map.put("k_rksl", e.getK_rksl());
                map.put("k_ckjg", e.getK_ckjg());
                map.put("k_cksl", e.getK_cksl());
                map.put("k_syl", e.getK_syl());
                map.put("k_kw", e.getK_kw());
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

    public String getKuCunById() throws ServletException, IOException {
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
                result.put("msg", "找不到库存信息");
            } else {
                Object obj = commServiceImpl.findById(CkKc.class, id);
                if (obj == null) {
                    result.clear();
                    result.put("result", -1);
                    result.put("msg", "找不到库存信息");
                } else {
                    CkKc e = (CkKc) obj;
                    result.put("id", e.getId());
                    result.put("cangku_id", e.getCangku_id());
                    result.put("cangku_mc", e.getCangku_mc());
                    result.put("k_bz", e.getK_bz());
                    result.put("k_dw", e.getK_dw());
                    result.put("k_gxys", e.getK_gxys());
                    result.put("k_gys", e.getK_gys());
                    result.put("k_gys_id", e.getK_gys_id());
                    result.put("k_pp", e.getK_pp());
                    result.put("k_rksl", e.getK_rksl());
                    result.put("k_scc", e.getK_scc());
                    result.put("k_wzmc", e.getK_wzmc());
                    result.put("k_xhgg", e.getK_xhgg());
                    result.put("rkdmx_id", e.getRkdmx_id());
                    result.put("wzzd_id", e.getWzzd_id());
                    result.put("k_rkjg", e.getK_rkjg());
                    result.put("k_rksl", e.getK_rksl());
                    result.put("k_ckjg", e.getK_ckjg());
                    result.put("k_cksl", e.getK_cksl());
                    result.put("k_syl", e.getK_syl());
                    result.put("k_kw", e.getK_kw());
                    List<CkKcMx> eList = commServiceImpl.getResult("from CkKcMx where kucun_id=" + e.getId(), null);
                    JSONArray mxArray = new JSONArray();
                    for (Object o : eList) {
                        CkKcMx x = (CkKcMx) o;
                        Map<String, Object> map = new HashMap();
                        map.put("id", x.getId());
                        map.put("xh", x.getXh());
                        map.put("sl", x.getSl());
                        map.put("zt", x.getZt());
                        map.put("bz", x.getBz());
                        mxArray.add(map);
                    }
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

    public String updateKuCun() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            CkKc e = (CkKc) commServiceImpl.findById(CkKc.class, jsonObj.optInt("id"));
            if (e == null) {
                result.clear();
                result.put("result", -1);
                result.put("msg", "找不到该库存记录");
            } else {
                e.setK_ckjg(jsonObj.optDouble("ckjg"));
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

    public String deleteKuCun() throws ServletException, IOException {
        JSONObject result = new JSONObject();
        try {
            if (!existsUser()) {
                return "login";
            }
            JSONObject jsonObj = JSONObject.fromObject(request.getParameter("jsonObj"));
            List<String> sqls = new ArrayList<String>();
            sqls.add("delete from CkKc where id=" + jsonObj.optInt("id"));
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
