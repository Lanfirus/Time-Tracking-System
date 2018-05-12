package ua.training.tts.model.entity;

import java.time.LocalDate;

public class TaskTeamLazy {

    public enum Status {
        ASSIGNED, FINISHED, EXPIRED, CANCELLED
    }

    public enum Approved {
        YES, NO
    }

    private Integer taskId;
    private String name;
    private LocalDate deadline;
    private Integer spentTime;
    private Integer employeeId;
    private Status status;
    private Approved state;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Approved getState() {
        return state;
    }

    public void setState(Approved state) {
        this.state = state;
    }
}
