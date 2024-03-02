package com.test.struts2;

import com.example.dao.UserDAO;
import com.example.models.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user = new User();
    private UserDAO userDAO;
    private User loggedInUser = new User();
    
    public UserAction() {
        userDAO = new UserDAO();
    }

    public String register() {
        if (user != null) {
            boolean registrationSuccessful = userDAO.saveUser(user);
            if (registrationSuccessful) {
            	addActionMessage("Registration Successful");
            	user = new User();
                return "user"; 
            } else {
            	addActionMessage("Registration Failed");
            	user = null;
                return "error"; 
            }
        }
        return "success";
    }
    public String userLogin() {
        if (user.getUsername() != null && user.getPassword() != null) {
            boolean loginSuccessful = userDAO.isUserValid(user.getUsername(), user.getPassword());
            if (loginSuccessful) {
                loggedInUser = userDAO.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
                if (loggedInUser != null) {
                    ActionContext.getContext().getSession().put("loggedInUser", loggedInUser);
                    addActionMessage("Welcome, " + loggedInUser.getUsername());
                    return "user";
                }
            }
            addActionError("Invalid username or password");
            return "error";
        }
        return "success";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public UserDAO getUserDAO() {
    	return userDAO;
    }
    
	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
