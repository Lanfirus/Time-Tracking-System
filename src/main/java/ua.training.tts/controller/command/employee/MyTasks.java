package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.listener.EmployeeDTO;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyTasks implements Command {

    private TaskService service;

    //ToDo Add logger

    public MyTasks(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Task task = null;

        if (request.getParameter(TableParameters.TASK_ID) != null) {
            task = service.buildTaskForUpdate(request);
        }
        if (task != null && detectStatusChange(request)) {
                request.setAttribute(ReqSesParameters.TASK_STATUS_HAS_BEEN_CHANGED, true);
        }
        else if (task != null) {
            try{
                service.tryToPutUpdateDataFromWebIntoDBEmployee(task, request);
                request.setAttribute(ReqSesParameters.TASK_UPDATE_OK, true);
            }
            catch (BadTaskDataException e){
                request.setAttribute(ReqSesParameters.BAD_TASK_UPDATE_DATA, true);
            }
        }
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        List<Task> list = service.findAllById(dto.getId());
        request.setAttribute(ReqSesParameters.TASK_LIST, list);
        return Pages.EMPLOYEE_MY_TASKS_PAGE;
    }

    private boolean detectStatusChange(HttpServletRequest request) {
        return (!request.getParameter(ReqSesParameters.TASK_OLD_STATUS).toLowerCase()
                .equals(service.getStatusByTaskId(Integer.parseInt(request.getParameter(ReqSesParameters.TASK_ID)))));
    }
}