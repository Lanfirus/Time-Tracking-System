package ua.training.tts.controller.command.redirect;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.service.FullTaskService;
import ua.training.tts.model.service.ProjectService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TaskEditFormAdmin implements Command {

    private TaskService service;

    //ToDo Add logger

    public TaskEditFormAdmin(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Task task = service.findById(Integer.parseInt(request.getParameter(ReqSesParameters.TASK_ID)));
        System.out.println(request.getParameter(ReqSesParameters.TASK_ID));
        if(task != null) {
            request.setAttribute(ReqSesParameters.TASK, task);
            ProjectService projectService = new ProjectService();
            List<Project> activeProjects = projectService.findAllActive();
            request.setAttribute(ReqSesParameters.ACTIVE_PROJECTS, activeProjects);
            return Pages.ADMIN_TASK_EDIT_PAGE;
        }
        else {
            return CommandParameters.REDIRECT + CommandParameters.MAIN;
        }
    }
}