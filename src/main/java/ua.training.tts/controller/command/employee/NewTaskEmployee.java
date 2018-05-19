package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.exception.DataChangeDetectedException;
import ua.training.tts.model.service.FullTaskService;
import ua.training.tts.model.service.ProjectService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class NewTaskEmployee implements Command {

    private TaskService service;

    //ToDo Add logger

    public NewTaskEmployee(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        Task task = service.buildNewTaskByEmployee(request, dto.getId());
        if (task.getDeadline() == null) {
            setProjectDeadline(task, request);
        }
        try {
            service.tryToPutTaskDataFromWebIntoDB(task, request);
            request.setAttribute(ReqSesParameters.TASK_REQUEST_OK, true);
        }
        catch (BadTaskDataException e) {
            service.setTaskEnteredDataBackToForm(request, task);
            request.setAttribute(ReqSesParameters.BAD_TASK_REQUEST_DATA, true);
        }
        catch (DataChangeDetectedException e) {
            request.setAttribute(ReqSesParameters.TASK_DATA_HAS_BEEN_CHANGED, true);
        }
        FullTaskService fullTaskService = new FullTaskService();
        List<FullTask> myProjects = fullTaskService.findAllProjectsByEmployeeId(dto.getId());
        request.setAttribute(ReqSesParameters.MY_PROJECTS, myProjects);
        return Pages.EMPLOYEE_REQUEST_NEW_TASK_PAGE;
    }

    private void setProjectDeadline(Task task, HttpServletRequest request){
        ProjectService service = new ProjectService();
        LocalDate deadline = service.findById(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                .getDeadline();
        task.setDeadline(deadline);
    }
}