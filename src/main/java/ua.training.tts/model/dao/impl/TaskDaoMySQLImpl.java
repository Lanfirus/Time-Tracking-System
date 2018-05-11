package ua.training.tts.model.dao.impl;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.util.RequestBuilder;
import ua.training.tts.model.util.builder.TaskBuilder;
import ua.training.tts.util.LogMessageHolder;

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
        List<String> fieldNames = getFieldNames();
        String request = builder.insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME)
                                .insertValues(fieldNames)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, task.getId());
            statement.setString(2, task.getName());
            statement.setInt(3, task.getProjectId());
            statement.setString(4, task.getStatus().name().toLowerCase());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setTime(6, Time.valueOf(task.getSpentTime()));
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
        //ToDO check whether Field == null is allowed here w/o Exception. Potential are ID, Patronymic and Comment
        Task task = builder.setId(set.getInt(TableParameters.TASK_ID))
                                    .setName(set.getString(TableParameters.TASK_NAME))
                                    .setProjectId(set.getInt(TableParameters.TASK_PROJECT_ID))
                                    .setStatus(set.getString(TableParameters.TASK_STATUS))
                                    .setDeadline(set.getDate(TableParameters.TASK_DEADLINE).toLocalDate())
                                    .setSpentTime(set.getTime(TableParameters.TASK_SPENT_TIME).toLocalTime())
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
    public List<Task> findAllById(Integer id) {
        List<Task> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                .join(TableParameters.TASK_TEAM_TABLE_NAME)
                                .on(TableParameters.TASK_TEAM_TASK_ID, TableParameters.TASK_ID)
                                .where(TableParameters.EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1,id);
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
        List<String> fieldNames = getFieldNames();
        String request = builder.update(TableParameters.TASK_TABLE_NAME, fieldNames)
                                .where(TableParameters.TASK_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, task.getId());
            statement.setString(2, task.getName());
            statement.setInt(3, task.getProjectId());
            statement.setString(4, task.getStatus().name().toLowerCase());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setTime(6, Time.valueOf(task.getSpentTime()));
            statement.setInt(7, task.getId());
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
        List<String> fieldNames = Arrays.asList(TableParameters.TASK_STATUS);
        String request = builder.update(TableParameters.TASK_TABLE_NAME, fieldNames)
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
        return Arrays.asList(Entity.TASK_ID, Entity.TASK_NAME, Entity.TASK_PROJECT_ID, Entity.TASK_STATUS,
                Entity.TASK_DEADLINE, Entity.TASK_SPENT_TIME);
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