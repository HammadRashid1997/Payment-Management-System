package com.test.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogOutAction extends ActionSupport {
    public String logOut() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.clear();
        session.put("loggedOut", true); 
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("your_cookie_name")) {
                    cookie.setMaxAge(0);
                    cookie.setValue(null);
                    cookie.setPath("/");
                    ServletActionContext.getResponse().addCookie(cookie);
                }
            }
        }

        return "success";
    }
}
