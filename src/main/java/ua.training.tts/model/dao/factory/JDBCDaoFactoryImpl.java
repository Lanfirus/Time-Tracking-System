package ua.training.tts.model.dao.factory;

import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.impl.EmployeeDaoMySQLImpl;
import ua.training.tts.model.dao.impl.TaskDaoMySQLImpl;
import ua.training.tts.model.util.RequestBuilder;


public class JDBCDaoFactoryImpl implements DaoFactory {

    public EmployeeDao createEmployeeDao() {
        return new EmployeeDaoMySQLImpl(new RequestBuilder());
    }

    public TaskDao createTaskDao() {
        return new TaskDaoMySQLImpl(new RequestBuilder());
    }
}