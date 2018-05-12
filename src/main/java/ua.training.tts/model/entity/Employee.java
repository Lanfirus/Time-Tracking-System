package ua.training.tts.model.entity;

import ua.training.tts.constant.model.dao.TableParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {

    public enum AccountRole {
        ADMIN, EMPLOYEE, UNKNOWN
    }

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String mobilePhone;
    private String comment;
    private AccountRole accountRole;

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

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    public List<String> getFieldNames() {
        return Arrays.asList(TableParameters.EMPLOYEE_LOGIN, TableParameters.EMPLOYEE_PASSWORD, TableParameters.EMPLOYEE_NAME,
                TableParameters.EMPLOYEE_SURNAME, TableParameters.EMPLOYEE_PATRONYMIC, TableParameters.EMPLOYEE_EMAIL,
                TableParameters.EMPLOYEE_MOBILE_PHONE, TableParameters.EMPLOYEE_COMMENT);
    }

    public List<String> getFieldNamesFull() {
        return Arrays.asList(TableParameters.EMPLOYEE_LOGIN, TableParameters.EMPLOYEE_PASSWORD, TableParameters.EMPLOYEE_NAME,
                TableParameters.EMPLOYEE_SURNAME, TableParameters.EMPLOYEE_PATRONYMIC, TableParameters.EMPLOYEE_EMAIL,
                TableParameters.EMPLOYEE_MOBILE_PHONE, TableParameters.EMPLOYEE_COMMENT, TableParameters.EMPLOYEE_ACCOUNT_ROLE);
    }

    /**
     * Returns all fields except for id and accountRole.
     * This method designed to be used for EMPLOYEE purposes where both those fields non accessible.
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