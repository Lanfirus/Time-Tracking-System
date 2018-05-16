package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadProjectDataException;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class NewProject implements Command {

    private ProjectService service;

    //ToDo Add logger

    public NewProject(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Project project = service.buildNewProject(request);
        try {
            service.tryToPutProjectDataFromWebIntoDB(project, request);
        }
        catch (BadProjectDataException e) {
            //ToDo throw Runtime and log
        }
        return CommandParameters.REDIRECT + CommandParameters.ADMIN_ALL_PROJECTS;
    }
}