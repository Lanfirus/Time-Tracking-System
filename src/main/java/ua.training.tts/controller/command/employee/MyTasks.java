package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.exception.DataChangeDetectedException;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@AccessRights(acceptedRoles = {Employee.AccountRole.EMPLOYEE}, isAvailableForGuests = false)
public class MyTasks implements Command {

    private TaskService service;

    //ToDo Add logger

    public MyTasks(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Task task = null;

        if (Objects.nonNull(request.getParameter(TableParameters.TASK_ID))) {
            task = service.buildTaskForUpdateByEmployee(request);
        }
        if (Objects.nonNull(task)) {
            try{
                service.tryToPutUpdateDataFromWebIntoDBEmployee(task, request);
                request.setAttribute(ReqSesParameters.TASK_UPDATE_OK, true);
            }
            catch (BadTaskDataException e){
                request.setAttribute(ReqSesParameters.BAD_TASK_UPDATE_DATA, true);
            }
            catch (DataChangeDetectedException e){
                request.setAttribute(ReqSesParameters.TASK_DATA_HAS_BEEN_CHANGED, true);
            }
        }
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        List<Task> list = service.findAllById(dto.getId());
        request.setAttribute(ReqSesParameters.TASK_LIST, list);
        return Pages.EMPLOYEE_MY_TASKS_PAGE;
    }
}