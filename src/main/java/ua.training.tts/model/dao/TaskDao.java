package ua.training.tts.model.dao;

import ua.training.tts.model.entity.Task;

import java.util.List;

public interface TaskDao extends GeneralDao<Task, Integer, String, String> {

    void setStatusById(Integer id, String status);

    public List<Task> findAllByEmployeeId(Integer id);

    public List<Task> findAllByStatus(String status);

    public List<Task> findAllByApprovalState(String approvalState);

    public void updateTaskEmployee(Task task);

}
