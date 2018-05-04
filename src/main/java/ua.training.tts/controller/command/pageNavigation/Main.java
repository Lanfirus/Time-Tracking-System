package ua.training.tts.controller.command.pageNavigation;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public class Main implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute(ReqSesParameters.ROLE);

        if (Employee.AccountRole.admin.toString().equals(role)) {
            return Pages.ADMIN_INDEX_PAGE;
        }
        if (Employee.AccountRole.employee.toString().equals(role)) {
            return Pages.EMPLOYEE_INDEX_PAGE;
        }
        if (Employee.AccountRole.unknown.toString().equals(role)) {
            request.setAttribute(ReqSesParameters.BAD_LOGIN_PASSWORD, true);
            return Pages.INDEX_PAGE;
        }
        return Pages.INDEX_PAGE;
    }
}