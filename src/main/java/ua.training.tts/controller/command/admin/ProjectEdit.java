package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;

public class ProjectEdit implements Command {

    private ProjectService service;

    //ToDo Add logger

    public ProjectEdit(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Project project = service.buildProject(request);
        try {
            service.tryToPutUpdateDataFromWebIntoDBEmployee(project, request);
        }
        catch (BadTaskDataException e) {
            //ToDo add logger and throw RuntimeException
        }
        return CommandParameters.REDIRECT + CommandParameters.ADMIN_ALL_PROJECTS;
    }
}