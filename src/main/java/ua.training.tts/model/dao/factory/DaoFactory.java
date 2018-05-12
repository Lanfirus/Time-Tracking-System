package ua.training.tts.model.dao.factory;

import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.TaskDao;


public interface DaoFactory {

   EmployeeDao createEmployeeDao();

   TaskDao createTaskDao();
}
