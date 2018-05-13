package ua.training.tts.model.dao.impl;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.FullTaskDao;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.util.RequestBuilder;
import ua.training.tts.model.util.builder.FullTaskBuilder;
import ua.training.tts.util.LogMessageHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FullTaskDaoMySQLImpl implements FullTaskDao {

    private RequestBuilder builder;
    private String savedStatement;

    public FullTaskDaoMySQLImpl(RequestBuilder builder){
        this.builder = builder;
    }

    @Override
    public void create(FullTask entity) {

    }

    @Override
    public FullTask findById(Integer integer) {
        return null;
    }

    @Override
    public List<FullTask> findAll() {
        List<FullTask> resultList = new ArrayList<>();
        String request = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                .join(TableParameters.PROJECT_TABLE_NAME)
                                .using(TableParameters.PROJECT_ID)
                                .join(TableParameters.EMPLOYEE_TABLE_NAME)
                                .join(TableParameters.EMPLOYEE_ID)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)){
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            while (set.next()){
                FullTask result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.PROJECT_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
        return resultList;
    }

    private FullTask extractDataFromResultSet(ResultSet set) throws SQLException {
        FullTaskBuilder builder = new FullTaskBuilder();
        FullTask fullTask = builder.setTaskId(set.getInt(TableParameters.TASK_ID))
                .setTaskName(set.getString(TableParameters.TASK_NAME))
                .setTaskDeadline(set.getDate(TableParameters.TASK_DEADLINE).toLocalDate())
                .setTaskSpentTime(set.getInt(TableParameters.TASK_SPENT_TIME))
                .setTaskStatus(set.getString(TableParameters.TASK_STATUS))
                .setTaskState(set.getString(TableParameters.TASK_APPROVED))

                .setEmployeeId(set.getInt(TableParameters.EMPLOYEE_ID))
                .setEmployeeLogin(set.getString(TableParameters.EMPLOYEE_LOGIN))
                .setEmployeePassword(set.getString(TableParameters.EMPLOYEE_PASSWORD))
                .setEmployeeName(set.getString(TableParameters.EMPLOYEE_NAME))
                .setEmployeeSurname(set.getString(TableParameters.EMPLOYEE_SURNAME))
                .setEmployeePatronymic(set.getString(TableParameters.EMPLOYEE_PATRONYMIC))
                .setEmployeeEmail(set.getString(TableParameters.EMPLOYEE_EMAIL))
                .setEmployeeMobilePhone(set.getString(TableParameters.EMPLOYEE_MOBILE_PHONE))
                .setEmployeeComment(set.getString(TableParameters.EMPLOYEE_COMMENT))
                .setEmployeeAccountRole(set.getString(TableParameters.EMPLOYEE_ACCOUNT_ROLE))

                .setProjectId(set.getInt(TableParameters.PROJECT_ID))
                .setProjectName(set.getString(TableParameters.PROJECT_NAME))
                .setProjectDeadline(set.getDate(TableParameters.PROJECT_DEADLINE).toLocalDate())
                .setProjectStatus(set.getString(TableParameters.PROJECT_STATUS))

                .buildFullTask();
        return fullTask;
    }

    @Override
    public void update(FullTask fullTask) {

    }

    @Override
    public void delete(Integer integer) {

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