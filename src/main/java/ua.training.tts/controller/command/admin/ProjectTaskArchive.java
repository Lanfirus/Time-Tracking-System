package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.service.FullTaskService;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@AccessRights(acceptedRoles = {Employee.AccountRole.ADMIN}, isAvailableForGuests = false)
public class ProjectTaskArchive implements Command {

    private FullTaskService service;

    public ProjectTaskArchive(FullTaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter(ReqSesParameters.PROJECT_ID))){
            Integer id = Integer.parseInt(request.getParameter(ReqSesParameters.PROJECT_ID));
            ProjectService projectService = new ProjectService();
            Project project = projectService.findById(id);
            if (project.getStatus() == (Project.Status.CANCELLED)
                    || project.getStatus() == (Project.Status.FINISHED)) {
                service.archiveProject(id);
            }
        }

        return Servlet.REDIRECT + CommandParameters.MAIN;
    }
}