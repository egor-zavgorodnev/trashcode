package com.tuneit.example.managed;

import lombok.Data;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
@Data
public class pfBean {
	private String name;
	private int tiredValue;

	public void onTiredChange() {
		System.out.println("tiredValue: " + tiredValue);
		if (tiredValue != 100) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Не пизди!", null));
		}

	}
}
