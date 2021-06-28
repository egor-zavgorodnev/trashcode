package com.tuneit.example.managed;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIInput;

@ManagedBean
@ViewScoped
@Data
public class LoggingBean {
	private UIInput firstName;
	private UIInput lastName;
	private UIInput age;
	private UICommand button;

	public void log() {
		System.out.println("Added new person. First name: " + firstName.getValue() + " Last name: " + lastName.getValue() + " Age: " + age.getValue());
	}
}
