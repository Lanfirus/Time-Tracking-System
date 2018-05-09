package ua.training.tts.dao.factory;

import org.junit.Assert;
import org.junit.Test;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.factory.JDBCDaoFactoryImpl;
import ua.training.tts.model.dao.impl.EmployeeDaoMySQLImpl;

public class JDBCDaoFactoryImplTest extends Assert{

    private JDBCDaoFactoryImpl factory = new JDBCDaoFactoryImpl();

    @Test
    public void createEmployeeDao(){
        EmployeeDao actuals = factory.createEmployeeDao();
        assertTrue(actuals instanceof EmployeeDao);
    }
}
