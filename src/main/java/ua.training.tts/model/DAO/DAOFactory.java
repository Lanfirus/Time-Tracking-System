package ua.training.tts.model.DAO;

import ua.training.tts.constant.model.dao.DAOParameters;
import ua.training.tts.model.DAO.DAOImpl.EmployeeDAOImpl;
import ua.training.tts.model.DAO.connectionPool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    public static GeneralDAO create(String typeOfDAO) {
        Connection connection = DataSource.getConnection();
        switch (typeOfDAO) {
            case DAOParameters.EMPLOYEE_DAO:
                return new EmployeeDAOImpl(connection);
//            case Constants.PREMADE_ORDERS_DAO : return new PremadeOrderDAO();
            default:
                throw new RuntimeException();
        }
    }
}
