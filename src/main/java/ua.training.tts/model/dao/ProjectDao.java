package ua.training.tts.model.dao;

import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;

import java.util.List;

public interface ProjectDao extends GeneralDao<Project, Integer, String, String> {

    public List<Project> findAllByEmployeeId(Integer id);

    public List<Project> findAllActive();

    public List<Project> findAllByStatus(String status);

    public List<Project> findAllArchived();

}
