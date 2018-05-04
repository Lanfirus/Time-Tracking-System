package ua.training.tts.model.dao.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.training.tts.constant.DBParameters;
import ua.training.tts.constant.ExceptionMessages;

import java.sql.Connection;

/**
 * Provides Connection pool functionality.
 * Uses HikariCP.
 */
public class ConnectionPool {

    private final static HikariConfig config = new HikariConfig();
    private final static HikariDataSource dataSource;

    static {
        config.setJdbcUrl(DBParameters.URL_CUSTOM);
        config.setUsername(DBParameters.NAME);
        config.setPassword(DBParameters.PASSWORD);
        config.addDataSourceProperty( DBParameters.CACHE_PREPARED_STATEMENT,
                DBParameters.CACHE_PREPARED_STATEMENT_VALUE);
        config.addDataSourceProperty( DBParameters.CACHE_SIZE_PREPARED_STATEMENT,
                DBParameters.CACHE_SIZE_PREPARED_STATEMENT_VALUE);
        config.addDataSourceProperty( DBParameters.CACHE_SQL_LIMIT_PREPARED_STATEMENT ,
                DBParameters.CACHE_SQL_LIMIT_PREPARED_STATEMENT_VALUE);
        dataSource = new HikariDataSource( config );
    }

    private ConnectionPool() {}

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        }
        catch(Exception e){
            throw new RuntimeException(ExceptionMessages.CONNECTION_PROBLEM);
        }
    }
}