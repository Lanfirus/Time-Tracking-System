package ua.training.tts.controller.command.redirect;

import ua.training.tts.constant.Pages;
import ua.training.tts.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationSuccessful implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Pages.REGISTRATION_SUCCESSFUL_PAGE;
    }
}