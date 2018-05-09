package ua.training.tts.controller.command;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.BadRegistrationDataException;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.util.LogMessageHolder;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {

    private EmployeeService service;

    public Registration(EmployeeService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Employee employee = service.buildEmployee(request);
        return tryToAddEmployeeRegistrationDataToDB(employee, request);
    }

    private String tryToAddEmployeeRegistrationDataToDB(Employee employee, HttpServletRequest request){
        try{
            service.onRecievingEmployeeRegistrationDataFromWeb(employee, request);
            log.info(LogMessageHolder.userRegistrationSuccessful(employee.getLogin()));
            return CommandParameters.REDIRECT + CommandParameters.REGISTRATION_SUCCESSFUL;
        }
        catch (NotUniqueLoginException e) {
            service.setEmployeeEnteredDataBackToForm(request, employee);
            request.setAttribute(CommandParameters.LOGIN_PROBLEM, true);
            log.warn(LogMessageHolder.userUsedExistingLogin(employee.getLogin()));
            return Pages.REGISTRATION_PAGE;
        }
        catch (BadRegistrationDataException e) {
            service.setEmployeeEnteredDataBackToForm(request, employee);
            log.warn(LogMessageHolder.userUsedNotExistingCredentials(employee.getLogin(), employee.getPassword()));
            return Pages.REGISTRATION_PAGE;
        }
    }
}