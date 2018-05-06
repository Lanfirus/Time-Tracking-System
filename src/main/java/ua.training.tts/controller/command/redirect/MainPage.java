package ua.training.tts.controller.command.redirect;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages to what page send each particular user after login, logout and requests of non-existing resources (such
 * requests are redirected to this class by MainServlet).
 *
 * There are two defined roles of employees in the system:
 * - Admin
 * - Employee
 * Also there are 2 other possible cases:
 * - Unknown means that user used incorrect login and/or password during Login stage.
 * - Null covering cases before login and after logout.
 */
public class MainPage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        String role = null;
        if (dto != null) {
            role = dto.getRole();
        }

        if (Entity.ACCOUNT_ROLE_ADMIN.equals(role)) {
            return Pages.ADMIN_INDEX_PAGE;
        }
        if (Entity.ACCOUNT_ROLE_EMPLOYEE.equals(role)) {
            return Pages.EMPLOYEE_INDEX_PAGE;
        }
        if (Entity.ACCOUNT_ROLE_UNKNOWN.equals(role)) {
            request.setAttribute(ReqSesParameters.BAD_LOGIN_PASSWORD, true);
            request.getSession().removeAttribute(ReqSesParameters.DTO);
            return Pages.INDEX_PAGE;
        }
        return Pages.INDEX_PAGE;
    }
}