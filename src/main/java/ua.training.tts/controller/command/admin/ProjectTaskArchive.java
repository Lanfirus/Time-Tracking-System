package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.service.FullTaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ProjectTaskArchive implements Command {

    private FullTaskService service;

    public ProjectTaskArchive(FullTaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter(ReqSesParameters.PROJECT_ID))) {
            Integer id = Integer.parseInt(request.getParameter(ReqSesParameters.PROJECT_ID));
            service.archiveProject(id);
        }
        return Servlet.REDIRECT + CommandParameters.MAIN;
    }
}