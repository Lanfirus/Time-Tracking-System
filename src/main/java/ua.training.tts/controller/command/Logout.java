package ua.training.tts.controller.command;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.util.LogMessageHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        //ToDo Implement disabling loggon for those with null login
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute(ReqSesParameters.LOGIN);
        session.invalidate();
        log.info(LogMessageHolder.userLogout(login));
        return CommandParameters.REDIRECT + CommandParameters.MAIN;
    }
}