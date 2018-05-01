package ua.training.tts.util;


import ua.training.tts.constant.DBParameters;
import ua.training.tts.constant.util.DBStatements;
import ua.training.tts.model.DAO.connectionPool.DataSource;

import java.sql.*;

/**
 * Utility class to initialize and de-initialize DB for program to operate and clean DB after usage.
 */
public class DBInitializator {

    private final static DBInitializator instance = new DBInitializator();
    private Connection connection;

    private DBInitializator(){}


    public static String getUrlDefault() {
        return DBParameters.URL_DEFAULT;
    }

    public static String getUrlCustom() {
        return DBParameters.URL_CUSTOM;
    }

    public static String getNAME() {
        return DBParameters.NAME;
    }

    public static String getPASSWORD() {
        return DBParameters.PASSWORD;
    }

    public Connection getDefaultConnection() throws SQLException{
        setDefaultConnectionToDB();
        return connection;
    }

    public Connection getCustomConnection(){
        try {
            setCustomConnectionToDB();
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    public Connection getValueOfConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDefaultConnectionToDB() throws SQLException {
        setConnection(DriverManager.getConnection(getUrlDefault(), getNAME(), getPASSWORD()));
    }

    public void setCustomConnectionToDB() throws SQLException{
//        setConnection(DriverManager.getConnection(getUrlCustom(), getNAME(), getPASSWORD()));
        setConnection(DataSource.getConnection());
    }

    public void closeConnectionToDB() throws SQLException{
        getValueOfConnection().close();
    }

    private void createDB() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(DBStatements.CREATE_DB_STATEMENT);
    }

    private void dropDB() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(DBStatements.DROP_DB_STATEMENT);
    }

    private void createTables() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(DBStatements.CREATE_EMPLOYEE_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_PROJECT_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_TASK_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_PROJECT_TEAM_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_TASK_TEAM_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_EMPLOYEE_ARCHIVE_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_PROJECT_ARCHIVE_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_TASK_ARCHIVE_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_PROJECT_TEAM_ARCHIVE_TABLE_STATEMENT);
        statement.executeUpdate(DBStatements.CREATE_TASK_DEADLINE_TRIGGER);
    }

    private void insertUserRecord(String record) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(record);
    }

    public void initializeDB(){
        try {
            getDefaultConnection();
            dropDB();
            createDB();
            closeConnectionToDB();
            getCustomConnection();
            createTables();
            insertUserRecord(DBStatements.INSERT_EMPLOYEE_RECORD_STATEMENT + DBStatements.INSERT_EMPLOYEE_RECORD1_VALUE);
            insertUserRecord(DBStatements.INSERT_EMPLOYEE_RECORD_STATEMENT + DBStatements.INSERT_EMPLOYEE_RECORD2_VALUE);
            insertUserRecord(DBStatements.INSERT_EMPLOYEE_RECORD_STATEMENT + DBStatements.INSERT_EMPLOYEE_RECORD3_VALUE);
            insertUserRecord(DBStatements.INSERT_PROJECT_RECORD_STATEMENT + DBStatements.INSERT_PROJECT_RECORD1_VALUE);
            insertUserRecord(DBStatements.INSERT_PROJECT_RECORD_STATEMENT + DBStatements.INSERT_PROJECT_RECORD2_VALUE);
            insertUserRecord(DBStatements.INSERT_PROJECT_RECORD_STATEMENT + DBStatements.INSERT_PROJECT_RECORD3_VALUE);
            insertUserRecord(DBStatements.INSERT_TASK_RECORD_STATEMENT + DBStatements.INSERT_TASK_RECORD1_VALUE);
            insertUserRecord(DBStatements.INSERT_TASK_RECORD_STATEMENT + DBStatements.INSERT_TASK_RECORD2_VALUE);
            insertUserRecord(DBStatements.INSERT_TASK_RECORD_STATEMENT + DBStatements.INSERT_TASK_RECORD3_VALUE);
            insertUserRecord(DBStatements.INSERT_TASK_RECORD_STATEMENT + DBStatements.INSERT_TASK_RECORD4_VALUE);
            insertUserRecord(DBStatements.INSERT_TASK_RECORD_STATEMENT + DBStatements.INSERT_TASK_RECORD5_VALUE);
            closeConnectionToDB();
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deInitializeDB(){
        try {
            getDefaultConnection();
            dropDB();
            closeConnectionToDB();
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static DBInitializator getInstance(){
        return instance;
    }
}
