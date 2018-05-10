package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.FilterParameters;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

public class EmployeeChangeRole implements Command {

    private EmployeeService service;

    public EmployeeChangeRole(EmployeeService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(ReqSesParameters.ID));
        String role = request.getParameter(ReqSesParameters.ACCOUNT_ROLE.toLowerCase());
        service.setRoleById(id, role);
        return Servlet.REDIRECT + CommandParameters.EMPLOYEES;
    }

    private void checkAndSetUpdateSuccessfulFlag(HttpServletRequest request){
        if (request.getHeader(FilterParameters.REFERER) != null &&
                request.getHeader(FilterParameters.REFERER).contains(CommandParameters.PROFILE)) {
            request.setAttribute(ReqSesParameters.PROFILE_UPDATE_OK, true);
        }
    }
}
