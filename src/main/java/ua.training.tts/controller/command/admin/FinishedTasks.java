package ua.training.tts.controller.command.admin;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FinishedTasks implements Command {

    private TaskService service;

    //ToDo Add logger

    public FinishedTasks(TaskService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Task> list = service.findAllByStatus(Task.Status.FINISHED.name().toLowerCase());
        request.setAttribute(ReqSesParameters.TASK_LIST, list);
        return Pages.ADMIN_FINISHED_TASKS_PAGE;
    }
}