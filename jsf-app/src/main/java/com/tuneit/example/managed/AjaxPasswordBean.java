package com.tuneit.example.managed;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;

@ManagedBean
@ViewScoped
@Data
public class AjaxPasswordBean {
	private UIInput password;

	public void ajaxListener() {
		System.out.println(password.getValue().toString().length());
	}
}
