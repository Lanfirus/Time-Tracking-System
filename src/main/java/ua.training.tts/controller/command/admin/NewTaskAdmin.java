package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.service.ProjectService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@AccessRights(acceptedRoles = {Employee.AccountRole.ADMIN}, isAvailableForGuests = false)
public class NewTaskAdmin implements Command {

    private TaskService service;

    //ToDo Add logger

    public NewTaskAdmin(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Task task = service.buildNewTaskForAdmin(request);
        if (task.getDeadline() == null) {
            setProjectDeadline(task, request);
        }
        try {
            service.tryToPutTaskDataFromWebIntoDB(task, request);
        }
        catch (BadTaskDataException e) {
            //ToDo throw Runtime and log
        }
        return CommandParameters.REDIRECT + CommandParameters.ADMIN_ALL_TASKS;
    }

    private void setProjectDeadline(Task task, HttpServletRequest request){
        ProjectService service = new ProjectService();
        LocalDate deadline = service.findById(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                .getDeadline();
        task.setDeadline(deadline);
    }
}