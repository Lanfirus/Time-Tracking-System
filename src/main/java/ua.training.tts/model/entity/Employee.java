package ua.training.tts.model.entity;

import ua.training.tts.constant.model.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {

    /**
     * Possible account roles
     */
    public enum AccountRole{admin, employee, unknown}

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String mobilePhone;
    private String comment;
    private String accountRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    public List<String> getFieldNames() {
        return Arrays.asList(Entity.EMPLOYEE_LOGIN, Entity.EMPLOYEE_PASSWORD, Entity.EMPLOYEE_NAME,
                Entity.EMPLOYEE_SURNAME, Entity.EMPLOYEE_PATRONYMIC, Entity.EMPLOYEE_EMAIL,
                Entity.EMPLOYEE_MOBILE_PHONE, Entity.EMPLOYEE_COMMENT);
    }

    /**
     * Returns all fields except for id and accountRole.
     * This method designed to be used for employee purposes where both those fields non accessible.
     * Both fields are part of full variant of this method.
     * @return
     */
    public List<String> getFieldValues(){
        List<String> list = new ArrayList<>();
        list.add(getLogin());
        list.add(getPassword());
        list.add(getName());
        list.add(getSurname());
        list.add(getPatronymic());
        list.add(getEmail());
        list.add(getMobilePhone());
        list.add(getComment());
        return list;
    }

    public List<Object> getFieldValuesFull(){
        List<Object> list = new ArrayList<>();
        list.add(getId());
        list.add(getLogin());
        list.add(getPassword());
        list.add(getName());
        list.add(getSurname());
        list.add(getPatronymic());
        list.add(getEmail());
        list.add(getMobilePhone());
        list.add(getComment());
        list.add(getAccountRole());
        return list;
    }
}