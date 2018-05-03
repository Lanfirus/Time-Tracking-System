package ua.training.tts.model.entity;

import ua.training.tts.constant.model.Entity;

import java.util.Map;

public class Employee {

    private Map<String, String> employeeData;

    public Employee(Map<String, String> employeeData){
        this.employeeData = employeeData;
    }

    public String getLogin(){
        return employeeData.get(Entity.EMPLOYEE_LOGIN);
    }

    public String getPassword(){
        return employeeData.get(Entity.EMPLOYEE_PASSWORD);
    }

    public String getName(){
        return employeeData.get(Entity.EMPLOYEE_NAME);
    }

    public String getSurname(){
        return employeeData.get(Entity.EMPLOYEE_SURNAME);
    }

    public String getPatronymic(){
        return employeeData.get(Entity.EMPLOYEE_PATRONYMIC);
    }

    public String getEmail(){
        return employeeData.get(Entity.EMPLOYEE_EMAIL);
    }

    public String getMobilePhone(){
        return employeeData.get(Entity.EMPLOYEE_MOBILE_PHONE);
    }

    public String getComment(){
        return employeeData.get(Entity.EMPLOYEE_COMMENT);
    }

    public Map<String, String> getEmployeeData(){
        return employeeData;
    }
}
