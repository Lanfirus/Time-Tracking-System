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
    String ADMIN_ALL_PROJECTS = "admin/project";
    String EMPLOYEE_NEW_TASK_FORM = "new_task_form";
    String EMPLOYEE_REQUEST_NEW_TASK = "request_new_task";
    String EMPLOYEE_MY_PROJECTS = "projects";
    String ADMIN_TASK_EDIT_FORM = "admin/task_edit_form";
    String ADMIN_TASK_EDIT = "admin/task_edit";
    String ADMIN_TASK_DELETE = "admin/task_delete";
    String ADMIN_NEW_TASK_FORM = "admin/new_task_form";
    String ADMIN_NEW_TASK = "admin/new_task";
    String ADMIN_NEW_PROJECT = "admin/new_project";
    String ADMIN_NEW_PROJECT_FORM = "admin/new_project_form";
    String ADMIN_PROJECT_EDIT_FORM = "admin/project_edit_form";
    String ADMIN_PROJECT_EDIT = "admin/project_edit";
    String ADMIN_PROJECT_DELETE = "admin/project_delete";
}
