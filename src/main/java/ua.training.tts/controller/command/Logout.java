package ua.training.tts.controller.command;

import ua.training.tts.constant.controller.command.CommandParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return CommandParameters.REDIRECT + CommandParameters.MAIN;
    }
}
