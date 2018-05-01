package ua.training.tts.controller.command;

import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dudchenko Andrei
 */
public class Registration implements Command {

    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        Employee Employee = new Employee(service.setUserData(request));
        return tryToAddEmployeeRegistrationDataToDB(Employee, request);
    }

    private String tryToAddEmployeeRegistrationDataToDB(Employee Employee, HttpServletRequest request){
        try{
            service.onRecievingEmployeeRegistrationDataFromWeb(Employee, request);
            return "/WEB-INF/jsp/registration-successful.jsp";
        }
        catch (NotUniqueLoginException e) {
            service.setEmployeeEnteredDataBackToForm(request, Employee);
            setProblemWithLoginAttribute(request);
            return "/WEB-INF/jsp/registration.jsp";
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setProblemWithLoginAttribute(HttpServletRequest request){
        request.setAttribute(CommandParameters.LOGIN_PROBLEM, true);
    }
}