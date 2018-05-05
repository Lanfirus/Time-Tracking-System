package ua.training.tts.controller.command;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.util.LogMessageHolder;
import ua.training.tts.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login implements Command {

    /**
     * Checks existing session at first, then check DB whether such employee exist and at the end set
     * role to "unknown" if all previous steps failed.
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        EmployeeService service = new EmployeeService();

        String login = request.getParameter(ReqSesParameters.LOGIN);
        String password = PasswordHashing.hashPassword(request.getParameter(ReqSesParameters.PASSWORD));
        String role;
        HttpSession session = request.getSession();

        if (session.getAttribute(ReqSesParameters.LOGIN) != null
                && session.getAttribute(ReqSesParameters.PASSWORD) != null
                && session.getAttribute(ReqSesParameters.ROLE) != null) {
            role = (String) session.getAttribute(ReqSesParameters.ROLE);
        }
        else if (service.isEmployeeExist(login, password)) {
            role = service.getRoleByLoginPassword(login, password);
            session.setAttribute(ReqSesParameters.LOGIN, login);
            session.setAttribute(ReqSesParameters.PASSWORD, password);
            session.setAttribute(ReqSesParameters.ROLE, role);
            log.info(LogMessageHolder.userLogin(login, password, role));
        }
        else {
            role = Employee.AccountRole.unknown.toString();
            session.setAttribute(ReqSesParameters.ROLE, role);
            log.info(LogMessageHolder.userLogin(login, password, role));
        }

        return CommandParameters.REDIRECT + CommandParameters.MAIN;
    }
}