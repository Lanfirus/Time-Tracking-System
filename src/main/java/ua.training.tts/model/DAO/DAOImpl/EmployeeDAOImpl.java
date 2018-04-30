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
        for (int parameterNumber = 1; parameterNumber <= parameterValues.length; parameterNumber++) {
            statement.setString(parameterNumber, parameterValues[parameterNumber]);
        }
    }

    @Override
    public Map<String, String> findById(int id) {
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(TableParameters.EMPLOYEE_ID).build();
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery();
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
        map.put(Entity.EMPLOYEE_ACCOUNT_ROLE, set.getString(Entity.EMPLOYEE_ACCOUNT_ROLE));
        return map;
    }

    @Override
    public List<Map<String, String>> findAll() {
        List<Map<String, String>> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME).build();
        try (Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery(request);
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
/*        Map<String, String> oldData = findByLogin(map.get(Constants.OLD_LOGIN));
        map.remove(Constants.OLD_LOGIN);
        User user = new User(map);
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET name = ? , surname = ? , patronymic = ? , login = ? , password = ? , comment = ? ," +
                        "homePhoneNumber = ? , mobilePhoneNumber = ? , email = ? WHERE login = ?")){
            statement.setString(1 , user.getName());
            statement.setString(2 , user.getSurame());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getComment());
            statement.setString(7, user.getHomePhoneNumber());
            statement.setString(8, user.getMobilePhoneNumber());
            statement.setString(9, user.getEmail());
            statement.setString(10, oldData.get(Constants.LOGIN));
            statement.executeUpdate();
            if (!map.get(Constants.LOGIN).equals(oldData.get(Constants.LOGIN))){
                PremadeOrderDAO dao = new PremadeOrderDAO();
                List<Map<String, String>> existingOrders = dao.findAllByLogin(oldData.get(Constants.LOGIN));
                existingOrders.stream().forEach(x -> {
                    x.put(Constants.LOGIN, map.get(Constants.LOGIN));
                    x.put(Constants.OLD_LOGIN, oldData.get(Constants.LOGIN));
                    dao.update(x);
                });
            }
        } catch (Throwable e) {
            create(oldData);
            throw new RuntimeException(e.getMessage());
       }*/
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users  WHERE id = ?")){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
