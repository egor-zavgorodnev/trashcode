package com.tuneit.example.managed;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "autowired")
@ApplicationScoped
public class AutowiredBean {
	public String sayHello() {
		return "Hello i am autowired bean";
	}
}
