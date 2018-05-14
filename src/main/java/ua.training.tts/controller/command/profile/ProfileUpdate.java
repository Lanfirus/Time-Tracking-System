package ua.training.tts.controller.command.profile;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.BadRegistrationDataException;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.util.LogMessageHolder;

import javax.servlet.http.HttpServletRequest;

public class ProfileUpdate implements Command {

    private EmployeeService service;
    EmployeeDTO dto;

    public ProfileUpdate(EmployeeService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Employee employee = service.buildEmployee(request);
        dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        employee.setId(dto.getId());
        return tryToAddEmployeeRegistrationDataToDB(employee, request);
    }

    private String tryToAddEmployeeRegistrationDataToDB(Employee employee, HttpServletRequest request){
        try{
            service.tryToPutUpdateDataFromWebIntoDB(employee, request);
            checkDTOAndChangeDataIfNeeded(employee, request);
            log.info(LogMessageHolder.userRegistrationSuccessful(employee.getLogin()));
            return CommandParameters.REDIRECT + CommandParameters.PROFILE;
        }
        catch (NotUniqueLoginException e) {
            service.setEmployeeEnteredDataBackToForm(request, employee);
            request.setAttribute(ReqSesParameters.NOT_UNIQUE_LOGIN, true);
            log.warn(LogMessageHolder.userUsedExistingLogin(employee.getLogin()));

        }
        catch (BadRegistrationDataException e) {
            service.setEmployeeEnteredDataBackToForm(request, employee);
            request.setAttribute(ReqSesParameters.BAD_REGISTRATION_DATA, true);
            log.warn(LogMessageHolder.userUsedNotExistingCredentials(employee.getLogin(), employee.getPassword()));
        }
        return selectPageToReturn(employee);
    }

    private void checkDTOAndChangeDataIfNeeded(Employee employee, HttpServletRequest request){
        if (!employee.getLogin().equals(dto.getLogin())) {
            dto.setLogin(employee.getLogin());
        }
        if (!employee.getPassword().equals(dto.getPassword())) {
            dto.setPassword(employee.getPassword());
        }
    }

    private String selectPageToReturn(Employee employee){
        if (Employee.AccountRole.ADMIN.equals(employee.getAccountRole())) {
            return Pages.PROFILE_ADMIN_PAGE;
        }
        return Pages.PROFILE_EMPLOYEE_PAGE;
    }
}