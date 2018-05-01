package ua.training.tts.controller.command.pageNavigation;

import ua.training.tts.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LoginForm implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/login.jsp";
    }
}
