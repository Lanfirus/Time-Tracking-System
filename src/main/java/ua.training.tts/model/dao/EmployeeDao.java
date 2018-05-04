package ua.training.tts.model.dao;

import ua.training.tts.model.entity.Employee;

public interface EmployeeDao extends GeneralDao<Employee, Integer, String, String> {

    boolean isEntryExist(String login, String password);
}
