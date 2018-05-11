package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyTasks implements Command {

    private TaskService service;

    public MyTasks(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Employee> list = service.findAll();
        request.setAttribute(ReqSesParameters.EMPLOYEE_LIST, list);
        return Pages.ADMIN_EMLOYEE_INFORMATION_PAGE;
    }
}