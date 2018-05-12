package ua.training.tts.model.dao.factory;

import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.impl.EmployeeDaoMySQLImpl;
import ua.training.tts.model.dao.impl.TaskDaoMySQLImpl;
import ua.training.tts.model.util.RequestBuilder;

import javax.servlet.annotation.WebFilter;


public class JDBCDaoFactoryImpl implements DaoFactory {

    @Override
    public EmployeeDao createEmployeeDao() {
        return new EmployeeDaoMySQLImpl(new RequestBuilder());
    }

    @Override
    public TaskDao createTaskDao() {
        return new TaskDaoMySQLImpl(new RequestBuilder());
    }
}