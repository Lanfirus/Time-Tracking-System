package ua.training.tts.controller.command.redirect;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProjectEditFormAdmin implements Command {

    private ProjectService service;

    //ToDo Add logger

    public ProjectEditFormAdmin(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Project project = service.findById(Integer.parseInt(request.getParameter(ReqSesParameters.PROJECT_ID)));
        if(project != null) {
            request.setAttribute(ReqSesParameters.PROJECT, project);
            return Pages.ADMIN_PROJECT_EDIT_PAGE;
        }
        else {
            return CommandParameters.REDIRECT + CommandParameters.MAIN;
        }
    }
}