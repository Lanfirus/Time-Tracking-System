package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.service.FullTaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AccessRights(acceptedRoles = {Employee.AccountRole.EMPLOYEE}, isAvailableForGuests = false)
public class Contacts implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return Pages.EMPLOYEE_CONTACTS;
    }
}