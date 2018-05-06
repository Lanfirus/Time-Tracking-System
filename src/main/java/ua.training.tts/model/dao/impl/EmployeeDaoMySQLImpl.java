package ua.training.tts.model.dao.impl;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.util.RequestBuilder;
import ua.training.tts.model.util.builder.EmployeeBuilder;
import ua.training.tts.util.LogMessageHolder;

import java.sql.*;
import java.util.*;

public class EmployeeDaoMySQLImpl implements EmployeeDao {

    private RequestBuilder builder = new RequestBuilder();
    private String savedStatement;

    public EmployeeDaoMySQLImpl(){
    }

    @Override
    public void create(Employee employee) {
        List<String> fieldNames = employee.getFieldNames();
        List<String> fieldValues = employee.getFieldValues();
        String request = builder.insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .insertValues(fieldNames)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            setValuesToPreparedStatement(statement, fieldValues);
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordInsertionToTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Service method to set required values into Prepared statement.
     * Pay attention that "list" index starts form 0 while "statement" index starts from 1, so there is "+1" in the code.
     * @param statement
     * @param fieldValues
     * @throws SQLException
     */
    private void setValuesToPreparedStatement(PreparedStatement statement, List<String> fieldValues)
            throws SQLException{
        for (int fieldNumber = 0; fieldNumber < fieldValues.size(); fieldNumber++) {
            statement.setString(fieldNumber + 1, fieldValues.get(fieldNumber));
        }
    }

    @Override
    public Employee findById(Integer id) {
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(TableParameters.EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1,id);
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            set.next();
            return extractDataFromResultSet(set);
        }
        catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    private Employee extractDataFromResultSet(ResultSet set) throws SQLException{
        EmployeeBuilder builder = new EmployeeBuilder();
        //ToDO check whether Field == null is allowed here w/o Exception. Potential are ID, Patronymic and Comment
        Employee employee = builder.setId(set.getInt(TableParameters.EMPLOYEE_ID))
                                    .setLogin(set.getString(TableParameters.EMPLOYEE_LOGIN))
                                    .setPassword(set.getString(TableParameters.EMPLOYEE_PASSWORD))
                                    .setName(set.getString(TableParameters.EMPLOYEE_NAME))
                                    .setSurame(set.getString(TableParameters.EMPLOYEE_SURNAME))
                                    .setPatronymic(set.getString(TableParameters.EMPLOYEE_PATRONYMIC))
                                    .setEmail(set.getString(TableParameters.EMPLOYEE_EMAIL))
                                    .setMobilePhone(set.getString(TableParameters.EMPLOYEE_MOBILE_PHONE))
                                    .setComment(set.getString(TableParameters.EMPLOYEE_COMMENT))
                                    .setAccountRole(set.getString(TableParameters.EMPLOYEE_ACCOUNT_ROLE))
                                    .buildEmployeeFull();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Employee result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                        savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
        return resultList;
    }

    /**
     * Updates Employee record in DB.
     * fieldValues doesn't have "ID" in itself, while it should be used as a key for request to DB.
     * For this purpose there is setInt command with "fieldValues.size() + 1" to setup ID.
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        List<String> fieldNames = employee.getFieldNames();
        List<String> fieldValues = employee.getFieldValues();
        String request = builder.update(TableParameters.EMPLOYEE_TABLE_NAME, fieldNames)
                                .where(TableParameters.EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            setValuesToPreparedStatement(statement, fieldValues);
            statement.setInt(fieldValues.size() + 1, employee.getId());
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordUpdatintInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public void delete(Integer id) {
        String request = builder.delete(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(TableParameters.EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, id);
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordDeletingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public Integer findIdByKeys(String... keyValues) {
        String[] keyNames = {TableParameters.EMPLOYEE_LOGIN, TableParameters.EMPLOYEE_PASSWORD};
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                 .where(keyNames[0])
                                 .and(keyNames[1])
                                 .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, keyValues[0]);
            statement.setString(2, keyValues[1]);
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(TableParameters.EMPLOYEE_ID);
        }
        catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public String findParamByKeys(String parameter, String... keyValues) {
        String[] keyNames = {TableParameters.EMPLOYEE_LOGIN, TableParameters.EMPLOYEE_PASSWORD};
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(keyNames[0])
                                .and(keyNames[1])
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, keyValues[0]);
            statement.setString(2, keyValues[1]);
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getString(parameter);
        }
        catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public boolean isEntryExist(String login, String password) {
        String[] keyNames = {TableParameters.EMPLOYEE_LOGIN, TableParameters.EMPLOYEE_PASSWORD};
        String request = builder.selectAllFromTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .where(keyNames[0])
                                .and(keyNames[1])
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, login);
            statement.setString(2, password);
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            set.next();
            set.getString(TableParameters.EMPLOYEE_ID);
            return true;
            }
        catch (SQLException e) {
            if (e.getMessage().equals(ExceptionMessages.ILLEGAL_OPERATION_ON_EMPTY_RESULT_SET)) {
                return false;
            }
            else {
                log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.EMPLOYEE_TABLE_NAME,
                                                                                            savedStatement), e);
                throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
            }
        }
    }
}