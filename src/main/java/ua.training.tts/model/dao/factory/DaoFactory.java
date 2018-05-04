package ua.training.tts.model.dao.factory;

import ua.training.tts.model.dao.EmployeeDao;


public interface DaoFactory {

   EmployeeDao createEmployeeDao();
}
