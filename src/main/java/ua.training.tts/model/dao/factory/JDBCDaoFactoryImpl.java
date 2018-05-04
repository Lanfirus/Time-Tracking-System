package ua.training.tts.model.dao.factory;

import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.impl.EmployeeDaoMySQLImpl;


public class JDBCDaoFactoryImpl implements DaoFactory {

    public EmployeeDao createEmployeeDao() {
        return new EmployeeDaoMySQLImpl();
    }
}