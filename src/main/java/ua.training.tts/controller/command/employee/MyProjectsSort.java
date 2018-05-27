package ua.training.tts.controller.command.employee;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.AccessRights;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.FullTask;
import ua.training.tts.model.service.FullTaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorts projects according to user preferences.
 */
@AccessRights(acceptedRoles = {Employee.AccountRole.EMPLOYEE}, isAvailableForGuests = false)
public class MyProjectsSort implements Command {

    private FullTaskService service;

    public MyProjectsSort(FullTaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDTO dto = (EmployeeDTO) request.getSession().getAttribute(ReqSesParameters.DTO);
        List<FullTask> list = service.findAllProjectsByEmployeeId(dto.getId());
        list = sort(list, request);
        request.setAttribute(ReqSesParameters.PROJECT_LIST, list);
        return Pages.EMPLOYEE_MY_PROJECTS_PAGE;
    }

    private List<FullTask> sort(List<FullTask> list, HttpServletRequest request){
        String sortField = request.getParameter(ReqSesParameters.SORT_FIELD);
        String sortOrder = request.getParameter(ReqSesParameters.SORT_ORDER);

        if (sortOrder.equals(CommandParameters.ASC)) {
            if(sortField.equals(TableParameters.PROJECT_ID)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectId))
                                    .collect(Collectors.toList());
            }
            else if(sortField.equals(TableParameters.PROJECT_NAME)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectName))
                                    .collect(Collectors.toList());
            }
            else if(sortField.equals(TableParameters.PROJECT_DEADLINE)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectDeadline))
                                    .collect(Collectors.toList());
            }
            else {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectStatus))
                                    .collect(Collectors.toList());
            }
        }
        else{
            if(sortField.equals(TableParameters.PROJECT_ID)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectId).reversed())
                                    .collect(Collectors.toList());
            }
            else if(sortField.equals(TableParameters.PROJECT_NAME)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectName).reversed())
                                    .collect(Collectors.toList());
            }
            else if(sortField.equals(TableParameters.PROJECT_DEADLINE)) {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectDeadline).reversed())
                                    .collect(Collectors.toList());
            }
            else {
                list = list.stream().sorted(Comparator.comparing(FullTask::getProjectStatus).reversed())
                                    .collect(Collectors.toList());
            }
        }

        return list;
    }
}