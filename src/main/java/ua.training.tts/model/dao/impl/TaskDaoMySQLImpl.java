package ua.training.tts.model.dao.impl;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.util.Annotation;
import ua.training.tts.model.util.RequestBuilder;
import ua.training.tts.model.util.builder.TaskBuilder;
import ua.training.tts.util.LogMessageHolder;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDaoMySQLImpl implements TaskDao {

    private RequestBuilder builder;
    private String savedStatement;

    public TaskDaoMySQLImpl(RequestBuilder builder){
        this.builder = builder;
    }

    @Override
    public void create(Task task) {
        String request = builder.insertIntoTable(TableParameters.TASK_TABLE_NAME)
                                .insertValues(getFieldNames())
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, task.getProjectId());
            statement.setInt(2, task.getEmployeeId());
            statement.setString(3, task.getName());
            statement.setString(4, task.getStatus().name().toLowerCase());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setInt(6, task.getSpentTime());
            statement.setString(7, task.getApproved().name().toLowerCase());
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordInsertionToTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task findById(Integer id) {
        String request = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                .where(TableParameters.TASK_ID)
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
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    private Task extractDataFromResultSet(ResultSet set) throws SQLException{
        TaskBuilder builder = new TaskBuilder();
        Task task = builder.setId(set.getInt(TableParameters.TASK_ID))
                           .setProjectId(set.getInt(TableParameters.TASK_PROJECT_ID))
                           .setEmployeeId(set.getInt(TableParameters.TASK_EMPLOYEE_ID))
                           .setName(set.getString(TableParameters.TASK_NAME))
                           .setStatus(set.getString(TableParameters.TASK_STATUS))
                           .setDeadline(set.getString(TableParameters.TASK_DEADLINE))
                           .setSpentTime(set.getInt(TableParameters.TASK_SPENT_TIME))
                           .setApproved(set.getString(TableParameters.TASK_APPROVED))
                           .buildTask();
        return task;
    }

    @Override
    public List<Task> findAll() {
        List<Task> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Task result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
        return resultList;
    }

    @Override
    public List<Task> findAllByEmployeeId(Integer id) {
        List<Task> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                .where(TableParameters.TASK_EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, id);
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Task result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
        return resultList;
    }

    @Override
    public void update(Task task) {
        String request = builder.update(TableParameters.TASK_TABLE_NAME, getFieldNames())
                                .where(TableParameters.TASK_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, task.getProjectId());
            statement.setInt(2, task.getEmployeeId());
            statement.setString(3, task.getName());
            statement.setString(4, task.getStatus().name().toLowerCase());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setInt(6, task.getSpentTime());
            statement.setString(7, task.getApproved().name().toLowerCase());
            statement.setInt(8, task.getId());
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordUpdatintInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public void updateTaskEmployee(Task task) {
        List<String> fieldNames = Arrays.asList(TableParameters.TASK_STATUS, TableParameters.TASK_SPENT_TIME);
        String request = builder.update(TableParameters.TASK_TABLE_NAME, fieldNames)
                                .where(TableParameters.TASK_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setString(1, task.getStatus().name().toLowerCase());
            statement.setInt(2, task.getSpentTime());
            statement.setInt(3, task.getId());
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordUpdatintInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public void delete(Integer id) {
        String request = builder.delete(TableParameters.TASK_TABLE_NAME)
                                .where(TableParameters.TASK_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, id);
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordDeletingInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    @Override
    public void setStatusById(Integer id, String status) {
        String request = builder.updateOne(TableParameters.TASK_TABLE_NAME, TableParameters.TASK_STATUS)
                                .where(TableParameters.TASK_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setString(1, status);
            statement.setInt(2, id);
            savedStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordUpdatintInTableProblem(TableParameters.TASK_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    public List<String> getFieldNames() {
        return Arrays.asList(TableParameters.TASK_PROJECT_ID, TableParameters.TASK_EMPLOYEE_ID,
                TableParameters.TASK_NAME, TableParameters.TASK_STATUS, TableParameters.TASK_DEADLINE,
                TableParameters.TASK_SPENT_TIME, TableParameters.TASK_APPROVED);
    }

    @Override
    public Integer findIdByKeys(String... strings) {
        return null;
    }

    @Override
    public String findParamByKeys(String s, String... strings) {
        return null;
    }
}