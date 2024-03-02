package com.test.struts2;

import java.util.Map;

import com.example.dao.AdminDAO;
import com.example.models.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
    private Admin admins = new Admin();
    private String role;

    public AdminAction() {
    	new AdminDAO();
        admins = new Admin();
    }
    
    public String adminLogin() {
        if ("admin".equals(admins.getUsername()) && "admin".equals(admins.getPassword())) {
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("username", admins.getUsername());
            session.put("password", admins.getPassword());
            return "admin";
        } else {
            addActionError("Invalid admin credentials");
            return "error";
        }
    }

    public String getRole() {
        return role;
    }

    public Admin getAdmins() {
        return admins;
    }

    public void setAdmins(Admin admins) {
        this.admins = admins;
    }
    
    public void setAdminDAO(AdminDAO adminDAO) {
    }
}
