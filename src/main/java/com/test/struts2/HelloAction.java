package com.test.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	private String name;
	private static final long serialVersionUID = 1L;
	
	public String SayHello() {
		return "success";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
