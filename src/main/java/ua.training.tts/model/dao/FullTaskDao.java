package ua.training.tts.model.dao;

import ua.training.tts.model.entity.full.FullTask;

import java.util.List;

public interface FullTaskDao extends GeneralDao<FullTask, Integer, String, String> {

    public List<FullTask> findAllProjectsByEmployeeId(Integer id);
}
