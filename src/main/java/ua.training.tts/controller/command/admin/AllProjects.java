package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.service.ProjectService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllProjects implements Command {

    private ProjectService service;

    //ToDo Add logger

    public AllProjects(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Project> list = service.findAll();
        System.out.println(list);
        request.setAttribute(ReqSesParameters.PROJECT_LIST, list);
        return Pages.ADMIN_ALL_PROJECTS_PAGE;
    }

}