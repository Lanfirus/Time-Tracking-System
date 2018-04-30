package ua.training.tts.model.DAO.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.training.tts.constant.DBParameters;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource{

    private final static HikariConfig config = new HikariConfig();
    private final static HikariDataSource ds;

    static {
        config.setJdbcUrl(DBParameters.URL_CUSTOM);
        config.setUsername(DBParameters.NAME);
        config.setPassword(DBParameters.PASSWORD);
        config.addDataSourceProperty( DBParameters.CACHE_PREPARED_STATEMENT , "true" );
        config.addDataSourceProperty( DBParameters.CACHE_SIZE_PREPARED_STATEMENT , "25" );
        config.addDataSourceProperty( DBParameters.CACHE_SQL_LIMIT_PREPARED_STATEMENT , "256" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
