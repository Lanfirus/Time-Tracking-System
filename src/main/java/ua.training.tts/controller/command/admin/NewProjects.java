package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class NewProjects implements Command {

    private ProjectService service;

    //ToDo Add logger

    public NewProjects(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Project> list = service.findAllByStatus(Project.Status.NEW.name());
        request.setAttribute(ReqSesParameters.PROJECT_LIST, list);
        return Pages.ADMIN_NEW_PROJECTS_PAGE;
    }

}