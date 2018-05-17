package ua.training.tts.util;

import java.sql.Connection;
import java.sql.SQLException;

public class RollbackGuarantee implements AutoCloseable {

    private Connection connection;
    private boolean committed;

    public RollbackGuarantee(Connection conn) {
        this.connection = conn;
    }

    public void commit() throws SQLException {
        connection.commit();
        committed = true;
    }

    @Override
    public void close() throws SQLException {
        if(!committed) {
            connection.rollback();
        }
    }

}
