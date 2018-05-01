package ua.training.tts.controller.command;

import ua.training.tts.constant.model.Entity;
import ua.training.tts.model.service.EmployeeService;
import ua.training.tts.model.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Login implements Command{

    private EmployeeService service = new EmployeeService();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = PasswordHashing.hashPassword(request.getParameter("password"));
        String role;
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
                role = (String) session.getAttribute("role");
            }
            else if (service.isEmployeeExist(login, password)) {
                role = service.getRoleByLoginPassword(login, password);
                request.getSession().setAttribute(Entity.EMPLOYEE_LOGIN, login);
                request.getSession().setAttribute(Entity.EMPLOYEE_PASSWORD, password);
                request.getSession().setAttribute(Entity.EMPLOYEE_ACCOUNT_ROLE, role);
            }
            else {
                role = "unknown";
            }
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        System.out.println(role);
        return "/index.jsp";
    }
}
