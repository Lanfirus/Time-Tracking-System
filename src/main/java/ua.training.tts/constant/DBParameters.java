package ua.training.tts.constant;

public interface DBParameters {

    String URL_DEFAULT = "jdbc:mysql://localhost/mysql";
    String URL_CUSTOM = "jdbc:mysql://localhost/time_tracking?characterEncoding=utf8";
    String NAME = "admin";
    String PASSWORD = "admin";

    String CACHE_PREPARED_STATEMENT = "cachePrepStmts";
    String CACHE_SIZE_PREPARED_STATEMENT = "prepStmtCacheSize";
    String CACHE_SQL_LIMIT_PREPARED_STATEMENT = "prepStmtCacheSqlLimit";
}
