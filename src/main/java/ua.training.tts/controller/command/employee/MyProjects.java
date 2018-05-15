package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.service.FullTaskService;
import ua.training.tts.model.service.ProjectService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyProjects implements Command {

    private FullTaskService service;

    //ToDo Add logger

    public MyProjects(FullTaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        List<FullTask> list = service.findAllProjectsByEmployeeId(dto.getId());
        request.setAttribute(ReqSesParameters.PROJECT_LIST, list);
        return Pages.EMPLOYEE_MY_PROJECTS_PAGE;
    }
}