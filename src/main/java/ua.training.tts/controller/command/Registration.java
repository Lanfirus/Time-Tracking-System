package ua.training.tts.controller.command;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.BadRegistrationDataException;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {

    private EmployeeService service = new EmployeeService();

    @Override
    public String execute(HttpServletRequest request) {
        Employee Employee = service.buildEmployee(request);
        return tryToAddEmployeeRegistrationDataToDB(Employee, request);
    }

    private String tryToAddEmployeeRegistrationDataToDB(Employee Employee, HttpServletRequest request){
        try{
            service.onRecievingEmployeeRegistrationDataFromWeb(Employee, request);
            return CommandParameters.REDIRECT + CommandParameters.REGISTRATION_SUCCESSFUL;
        }
        catch (NotUniqueLoginException e) {
            service.setEmployeeEnteredDataBackToForm(request, Employee);
            request.setAttribute(CommandParameters.LOGIN_PROBLEM, true);
            return Pages.REGISTRATION_PAGE;
        }
        catch (BadRegistrationDataException e) {
            service.setEmployeeEnteredDataBackToForm(request, Employee);
            request.setAttribute(CommandParameters.LOGIN_PROBLEM, true);
            return Pages.REGISTRATION_PAGE;
        }
    }
}