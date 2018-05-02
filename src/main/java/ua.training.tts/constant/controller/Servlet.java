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
    String MAIN = "main";
    String LOGIN_FORM = "login_form";
    String LOGIN = "login";
    String LOGOUT = "logout";
}
