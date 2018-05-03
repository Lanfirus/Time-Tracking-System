package ua.training.tts.model.util;

import java.util.Map;

public class RequestBuilder {

    private StringBuilder request;

    public RequestBuilder(){
        request = new StringBuilder();
    }

    public RequestBuilder insertIntoTable(String tableName){
        request.append("INSERT INTO `")
               .append(tableName)
               .append("` ");
        return this;
    }

    public RequestBuilder insertValues(String[] fieldNames){
        request.append("(");
        for (int fieldNumber = 0; fieldNumber < fieldNames.length; fieldNumber++) {
            request.append("`")
                   .append(fieldNames[fieldNumber])
                   .append("`");
            if (fieldNumber != fieldNames.length - 1){
                request.append(",");
            }
        }
        request.append(") VALUES (");
        for (int fieldNumber = 0; fieldNumber < fieldNames.length; fieldNumber++) {
            request.append("?");
            if (fieldNumber != fieldNames.length - 1){
                request.append(",");
            }
        }
        request.append(")");
        return this;
    }

    public RequestBuilder selectAllFromTable(String tableName) {
        request.append("SELECT * FROM ")
               .append(tableName);
        return this;
    }

    public RequestBuilder where(String columnName) {
        request.append(" WHERE ")
               .append(columnName)
               .append(" = ?");
        return this;
    }

    public RequestBuilder and(String columnName) {
        request.append(" and ")
                .append(columnName)
                .append(" = ?");
        return this;
    }

    public RequestBuilder update(String tableName, String[] fieldNames) {
        request.append("UPDATE ")
                .append(tableName)
                .append(" SET ");
        for (int fieldNumber = 0; fieldNumber < fieldNames.length; fieldNumber++) {
            request.append(fieldNames[fieldNumber])
                   .append(" = ?");
            if (fieldNumber != fieldNames.length - 1) {
                request.append(",");
            }
        }
        request.append(" ");
        return this;
    }

    public RequestBuilder delete(String columnName) {
        request.append("DELETE FROM ")
                .append(columnName)
                .append(" ");
        return this;
    }

    public String build(){
        return request.toString();
    }
}
