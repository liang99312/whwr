package com.whwr.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.whwr.domain.A01;

public class BaseAction extends DefaultActionSupport implements ServletRequestAware, ServletResponseAware {

    public HttpServletRequest request;
    public HttpServletResponse response;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public boolean existsUser() {
        boolean result = false;
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("a01");
        if (loginUser != null) {
            result = true;
        }
        return result;
    }

    public A01 getDlA01() {
        HttpSession session = request.getSession();
        return (A01) session.getAttribute("a01");

    }

    public String getUserName() {
        String result = "";
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("a01");
        if (loginUser != null && !"".equals(((A01) loginUser).getBh())) {
            result = ((A01) loginUser).getBh();
        }
        return result;

    }

    public boolean isSuperUser() {
        return "sa".equals(getUserName());
    }

    public JSONObject getJsonObject() {
        return JSONObject.fromObject(request.getParameter("jsonObj"));
    }

    public void renderJson(String jsonString) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.close();
    }
}
