package ua.training.tts.model.DAO.DAOImpl;

import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.DAO.GeneralDAO;
import ua.training.tts.model.util.RequestBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl implements GeneralDAO {

    private Connection connection;
    private RequestBuilder builder = new RequestBuilder();

    public EmployeeDAOImpl(Connection connection){
        this.connection = connection;
    }

    /**
     * Creates user based on provided data.
     * @param map
     */
    @Override
    public void create(Map<String, String> map){
        String[] parameterNames = map.keySet().stream().toArray(String[]::new);
        String[] parameterValues = map.values().stream().toArray(String[]::new);
        String request = builder.insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME)
               .insertValues(parameterNames).build();
        try (PreparedStatement statement = connection.prepareStatement(request)) {
            setValuesToPreparedStatement(statement, parameterValues);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValuesToPreparedStatement(PreparedStatement statement, String[] parameterValues)
            throws SQLException{
        for (int parameterNumber = 0; parameterNumber < parameterValues.length; parameterNumber++) {
            System.out.println(parameterNumber);
            System.out.println(parameterValues[parameterNumber]);
            statement.setString(parameterNumber + 1, parameterValues[parameterNumber]);
        }
    }



    @Override
    public Map<String, String> findById(int id) {
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(TableParameters.EMPLOYEE_ID).build();
        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            set.next();
            return extractDataFromResultSet(set);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> extractDataFromResultSet(ResultSet set) throws SQLException{
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Entity.EMPLOYEE_ID, set.getString(Entity.EMPLOYEE_ID));
        map.put(Entity.EMPLOYEE_LOGIN, set.getString(Entity.EMPLOYEE_LOGIN));
        map.put(Entity.EMPLOYEE_PASSWORD, set.getString(Entity.EMPLOYEE_PASSWORD));
        map.put(Entity.EMPLOYEE_NAME, set.getString(Entity.EMPLOYEE_NAME));
        map.put(Entity.EMPLOYEE_SURNAME, set.getString(Entity.EMPLOYEE_SURNAME));
        map.put(Entity.EMPLOYEE_PATRONYMIC, set.getString(Entity.EMPLOYEE_PATRONYMIC));
        map.put(Entity.EMPLOYEE_EMAIL, set.getString(Entity.EMPLOYEE_EMAIL));
        map.put(Entity.EMPLOYEE_MOBILE_PHONE, set.getString(Entity.EMPLOYEE_MOBILE_PHONE));
        map.put(Entity.EMPLOYEE_COMMENT, set.getString(Entity.EMPLOYEE_COMMENT));
        map.put(Entity.EMPLOYEE_ACCOUNT_ROLE, set.getString(Entity.EMPLOYEE_ACCOUNT_ROLE));
        return map;
    }

    @Override
    public List<Map<String, String>> findAll() {
        List<Map<String, String>> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME).build();
        try (PreparedStatement statement = connection.prepareStatement(request)){
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Map<String, String> result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Map<String, String> map) {
        String login = map.get(TableParameters.EMPLOYEE_OLD_LOGIN);
        String password = map.get(TableParameters.EMPLOYEE_OLD_PASSWORD);
        map.remove(TableParameters.EMPLOYEE_OLD_LOGIN);
        map.remove(TableParameters.EMPLOYEE_OLD_PASSWORD);
        String[] parameterNames = map.keySet().stream().toArray(String[]::new);
        String[] parameterValues = map.values().stream().toArray(String[]::new);
        String request = builder.update(TableParameters.EMPLOYEE_TABLE_NAME, parameterNames)
                                .where(TableParameters.EMPLOYEE_ID).build();
        try (PreparedStatement statement = connection.prepareStatement(request)){
            setValuesToPreparedStatement(statement, parameterValues);
            statement.setString(parameterValues.length + 1,
                    findParamByLoginPassword(Entity.EMPLOYEE_ID, login, password));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
       }
    }

    public String findParamByLoginPassword(String parameter, String login, String password){
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                .where(TableParameters.EMPLOYEE_LOGIN).and(TableParameters.EMPLOYEE_PASSWORD).build();
        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, login);
            statement.setString(2, password);
            System.out.println(statement);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getString(parameter);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String request = builder.delete(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(TableParameters.EMPLOYEE_ID).build();
        try (PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
