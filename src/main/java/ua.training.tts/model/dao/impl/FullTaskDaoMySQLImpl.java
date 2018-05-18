package ua.training.tts.model.dao.impl;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.FullTaskDao;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.full.FullTask;
import ua.training.tts.model.util.RequestBuilder;
import ua.training.tts.model.util.builder.FullTaskBuilder;
import ua.training.tts.util.LogMessageHolder;
import ua.training.tts.util.RollbackGuarantee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<FullTask> findAllProjectsByEmployeeId(Integer id) {
        List<FullTask> resultList = new ArrayList<>();
        List<String> columnNames = Arrays.asList(TableParameters.PROJECT_ID, TableParameters.PROJECT_NAME,
                TableParameters.PROJECT_DEADLINE, TableParameters.PROJECT_STATUS);
        String request = builder.selectSomeFromTableDistinct(TableParameters.TASK_TABLE_NAME, columnNames)
                                .join(TableParameters.PROJECT_TABLE_NAME)
                                .using(TableParameters.PROJECT_ID)
                                .where(TableParameters.EMPLOYEE_ID)
                                .and(TableParameters.PROJECT_STATUS)
                                .build();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)){
            statement.setInt(1, id);
            statement.setString(2, Project.Status.ASSIGNED.name().toLowerCase());
            savedStatement = statement.toString();
            ResultSet set = statement.executeQuery();
            while (set.next()){
                FullTask result = extractProjectDataFromResultSet(set);
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

    private FullTask extractProjectDataFromResultSet(ResultSet set) throws SQLException {
        FullTaskBuilder builder = new FullTaskBuilder();
        FullTask fullTask = builder.setProjectId(set.getInt(TableParameters.PROJECT_ID))
                                    .setProjectName(set.getString(TableParameters.PROJECT_NAME))
                                    .setProjectDeadline(set.getDate(TableParameters.PROJECT_DEADLINE).toLocalDate())
                                    .setProjectStatus(set.getString(TableParameters.PROJECT_STATUS))
                                    .buildProject();
        return fullTask;
    }

    private FullTask extractProjectTaskDataFromResultSet(ResultSet set) throws SQLException {
        FullTaskBuilder builder = new FullTaskBuilder();
        FullTask fullTask = builder.setTaskId(set.getInt(TableParameters.TASK_ID))
                                   .setTaskName(set.getString(TableParameters.TASK_NAME))
                                   .setTaskDeadline(set.getDate(TableParameters.TASK_DEADLINE).toLocalDate())
                                   .setTaskSpentTime(set.getInt(TableParameters.TASK_SPENT_TIME))
                                   .setTaskStatus(set.getString(TableParameters.TASK_STATUS))
                                   .setTaskState(set.getString(TableParameters.TASK_APPROVED))
                                   .setEmployeeId(set.getInt(TableParameters.EMPLOYEE_ID))

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

    @Override
    public void archiveProjectAndTasks(Integer id) {

        try (Connection connection = ConnectionPool.getConnection();
                RollbackGuarantee guarantee = new RollbackGuarantee(connection) ){
            connection.setAutoCommit(false);
            String requestGetProjectTaskData = builder.selectAllFromTable(TableParameters.TASK_TABLE_NAME)
                                    .join(TableParameters.PROJECT_TABLE_NAME)
                                    .using(TableParameters.PROJECT_ID)
                                    .where(TableParameters.PROJECT_ID)
                                    .build();
            PreparedStatement statementGetProjectData = connection.prepareStatement(requestGetProjectTaskData);
            statementGetProjectData.setInt(1,id);
            savedStatement = statementGetProjectData.toString();
            ResultSet set = statementGetProjectData.executeQuery();
            List<FullTask> projectTaskData = new ArrayList<>();
            while (set.next()){
                FullTask result = extractProjectTaskDataFromResultSet(set);
                projectTaskData.add(result);
            }
            set.close();
            builder.clear();


            String requestPutProjectDataToArchive = builder.insertIntoTable(TableParameters.PROJECT_ARCHIVE_TABLE_NAME)
                                                    .insertValues(getProjectFieldNames())
                                                    .build();
            PreparedStatement statementPutProjectDataToArchive = connection.prepareStatement(requestPutProjectDataToArchive);
            statementPutProjectDataToArchive.setInt(1, id);
            statementPutProjectDataToArchive.setString(2, projectTaskData.get(0).getProjectName());
            statementPutProjectDataToArchive.setDate(3, Date.valueOf(projectTaskData.get(0).getProjectDeadline()));
            statementPutProjectDataToArchive.setString(4, projectTaskData.get(0).getProjectStatus().name().toLowerCase());
            savedStatement += statementPutProjectDataToArchive.toString();
            statementPutProjectDataToArchive.executeUpdate();
            builder.clear();

            for (int i = 0; i < projectTaskData.size(); i++) {
                String requestPutTaskDataToArchive = builder.insertIntoTable(TableParameters.TASK_ARCHIVE_TABLE_NAME)
                                        .insertValues(getTaskFieldNames())
                                        .build();
                PreparedStatement statementPutTaskDataToArchive = connection.prepareStatement(requestPutTaskDataToArchive);
                statementPutTaskDataToArchive.setInt(1, projectTaskData.get(i).getTaskId());
                statementPutTaskDataToArchive.setInt(2, projectTaskData.get(i).getProjectId());
                statementPutTaskDataToArchive.setInt(3, projectTaskData.get(i).getEmployeeId());
                statementPutTaskDataToArchive.setString(4, projectTaskData.get(i).getTaskName());
                statementPutTaskDataToArchive.setString(5, projectTaskData.get(i).getTaskStatus().name().toLowerCase());
                statementPutTaskDataToArchive.setDate(6, Date.valueOf(projectTaskData.get(i).getTaskDeadline()));
                statementPutTaskDataToArchive.setInt(7, projectTaskData.get(i).getTaskSpentTime());
                statementPutTaskDataToArchive.setString(8, projectTaskData.get(i).getTaskApprovalState().name().toLowerCase());
                savedStatement += statementPutTaskDataToArchive.toString();
                statementPutTaskDataToArchive.executeUpdate();
                builder.clear();
            }

            String requestDeleteProject = builder.delete(TableParameters.PROJECT_TABLE_NAME)
                                                 .where(TableParameters.PROJECT_ID)
                                                 .build();
            PreparedStatement statementDeleteProject = connection.prepareStatement(requestDeleteProject);
            statementDeleteProject.setInt(1, id);
            savedStatement += statementDeleteProject.toString();
            statementDeleteProject.executeUpdate();
            builder.clear();

            guarantee.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e) {
            log.error(LogMessageHolder.recordSearchingInTableProblem(TableParameters.PROJECT_TABLE_NAME,
                    savedStatement), e);
            throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
        }
    }

    private List<String> getProjectFieldNames() {
        return Arrays.asList(TableParameters.PROJECT_ID, TableParameters.PROJECT_NAME, TableParameters.PROJECT_DEADLINE,
                TableParameters.PROJECT_STATUS);
    }

    private List<String> getTaskFieldNames() {
        return Arrays.asList(TableParameters.TASK_ID, TableParameters.TASK_PROJECT_ID, TableParameters.TASK_EMPLOYEE_ID,
                TableParameters.TASK_NAME, TableParameters.TASK_STATUS, TableParameters.TASK_DEADLINE,
                TableParameters.TASK_SPENT_TIME, TableParameters.TASK_APPROVED);
    }
}