package ua.training.tts.constant.controller;

public interface Servlet {

    String URL_PATTERN = "/tts/*";
    String URI_REPLACE_PATTERN = ".*/tts/";
    String REPLACEMENT = "";
    String INDEX_PAGE = "/index.jsp";
    String REDIRECT = "redirect";

    String REGISTRATION_FORM = "registration_form";
    String REGISTRATION = "registration";
    String REGISTRATION_SUCCESSFUL = "registration_successful";
    String SERVLET_MAIN = "/company/tts/main";
    String MAIN = "main";
    String LOGIN_FORM = "login_form";
    String LOGIN = "login";
    String LOGOUT = "logout";
    String PROFILE = "profile";
    String PROFILE_UPDATE = "profile_update";
    String ADMIN_EMPLOYEE_INFORMATION = "admin/employees";
    String ADMIN_EMPLOYEE_CHANGE_ROLE = "admin/employee_change_role";
    String ADMIN_EMPLOYEE_DELETE = "admin/employee_delete";
    String EMPLOYEE_MY_TASKS = "task_show";
    String EMPLOYEE_MY_TASKS_UPDATE = "employee_task_update";
    String ADMIN_ALL_TASKS = "admin/task";
}
