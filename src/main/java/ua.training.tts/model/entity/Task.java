package ua.training.tts.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {

    public enum Status {
        NOT_ASSIGNED, ASSIGNED, FINISHED, EXPIRED, CANCELLED
    }

    private Integer id;
    private String name;
    private Integer projectId;
    private Status status;
    private LocalDate deadline;
    private LocalTime spentTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalTime getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(LocalTime spentTime) {
        this.spentTime = spentTime;
    }
}
