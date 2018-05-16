package ua.training.tts.controller.command.redirect;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.util.EmployeeDTO;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.service.FullTaskService;
import ua.training.tts.model.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class NewProjectFormAdmin implements Command {

    public NewProjectFormAdmin() {
    }

    @Override
    public String execute(HttpServletRequest request) {

        return Pages.ADMIN_NEW_PROJECT;
    }
}