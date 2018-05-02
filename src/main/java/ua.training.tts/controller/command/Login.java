package ua.training.tts.controller.command;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.model.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login implements Command{

    private EmployeeService service = new EmployeeService();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(CommandParameters.LOGIN);
        String password = PasswordHashing.hashPassword(request.getParameter(CommandParameters.PASSWORD));
        String role;
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute(CommandParameters.LOGIN) != null
                    && session.getAttribute(CommandParameters.PASSWORD) != null) {
                role = (String) session.getAttribute(Entity.EMPLOYEE_ACCOUNT_ROLE);
            }
            else if (service.isEmployeeExist(login, password)) {
                role = service.getRoleByLoginPassword(login, password);
                session.setAttribute(Entity.EMPLOYEE_LOGIN, login);
                session.setAttribute(Entity.EMPLOYEE_PASSWORD, password);
                session.setAttribute(Entity.EMPLOYEE_ACCOUNT_ROLE, role);
            }
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return Pages.INDEX_PAGE;
    }
}
