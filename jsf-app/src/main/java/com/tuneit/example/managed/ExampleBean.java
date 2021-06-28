package com.tuneit.example.managed;

import com.tuneit.example.model.Employee;
import lombok.Data;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class ExampleBean {

    private String foolMessage = "";
    private String chosenText = "оба варианта";

    @ManagedProperty("#{autowired}")
    private AutowiredBean autowiredBean;

    @ManagedProperty("#{loggingBean}")
    private LoggingBean loggingBean;

    private Employee newEmployee = new Employee(); // for adding
    private Employee editableEmployee; // for editing
    private List<Employee> employees = new ArrayList<Employee>();

    private String somePassword;

    public void addEmployee() {
        loggingBean.log();
        employees.add(newEmployee);
        newEmployee = new Employee();
    }

    public String navigateToPrimeFaces() {
        return "primeFaces";
    }

    public void makeFool() {
        foolMessage = "ххахаххах ебать ты дурачек";
    }

    public void deleteEmployee() {
        employees.remove(employees.size() - 1);
    }

    public String callAutowiredBean() {
        return autowiredBean.sayHello();
    }

    public void validate(FacesContext context, UIComponent comp,
                         Object value) {

        System.out.println("inside validate method");

        String val = (String) value;

        if (val.length() > 0) {
            ((UIInput) comp).setValid(false);

            FacesMessage message = new FacesMessage(
                    "А ты хорош!");
            context.addMessage(comp.getClientId(context), message);

        }
    }

    public void change(String value) {
        System.out.println("changed");
        chosenText = value;
    }

    public String editEmployee() {
        return null;
    }

    public String saveEmployees() {

        for (Employee employee : employees) {
            employee.setCanEdit(false);
        }
        return null;
    }


    public String submit() {
        return null;
    }

}
