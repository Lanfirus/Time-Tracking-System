package ua.training.tts.model.util.builder;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.entity.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Builder class for Employee entity
 */
public class TaskBuilder {

    private Integer id;
    private String name;
    private Integer projectId;
    private Task.Status status;
    private LocalDate deadline;
    private LocalTime spentTime;

    public TaskBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder setProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public TaskBuilder setStatus(String status) {
        if (Task.Status.NOT_ASSIGNED.name().equalsIgnoreCase(status)) {
            this.status = Task.Status.NOT_ASSIGNED;
        } else if (Task.Status.ASSIGNED.name().equalsIgnoreCase(status)) {
            this.status = Task.Status.ASSIGNED;
        } else if (Task.Status.FINISHED.name().equalsIgnoreCase(status)) {
            this.status = Task.Status.FINISHED;
        } else if (Task.Status.EXPIRED.name().equalsIgnoreCase(status)) {
            this.status = Task.Status.EXPIRED;
        } else if (Task.Status.CANCELLED.name().equalsIgnoreCase(status)) {
            this.status = Task.Status.CANCELLED;
        }
        return this;
    }

    public TaskBuilder setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public TaskBuilder setSpentTime(LocalTime spentTime) {
        this.spentTime = spentTime;
        return this;
    }

    public Task buildTask(){
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setProjectId(projectId);
        task.setStatus(status);
        task.setDeadline(deadline);
        task.setSpentTime(spentTime);
        return task;
    }
}
