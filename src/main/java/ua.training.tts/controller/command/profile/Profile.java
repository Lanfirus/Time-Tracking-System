package ua.training.tts.controller.command.profile;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.FilterParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

@AccessRights(acceptedRoles = {Employee.AccountRole.ADMIN, Employee.AccountRole.EMPLOYEE}, isAvailableForGuests = false)
public class Profile implements Command {

    private EmployeeService service;

    public Profile(EmployeeService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        Employee employee = service.findByLogin(dto.getLogin());
        checkAndSetUpdateSuccessfulFlag(request);
        service.setEmployeeEnteredDataBackToForm(request, employee);
        if (Employee.AccountRole.ADMIN.name().equalsIgnoreCase(dto.getRole().name())) {
            return Pages.PROFILE_ADMIN_PAGE;
        }
            return Pages.PROFILE_EMPLOYEE_PAGE;
    }

    private void checkAndSetUpdateSuccessfulFlag(HttpServletRequest request){
        if (request.getHeader(FilterParameters.REFERER) != null &&
                request.getHeader(FilterParameters.REFERER).contains(CommandParameters.PROFILE)) {
            request.setAttribute(ReqSesParameters.PROFILE_UPDATE_OK, true);
        }
    }
}
