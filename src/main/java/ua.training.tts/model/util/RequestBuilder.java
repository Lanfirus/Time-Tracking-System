package ua.training.tts.model.util;

import ua.training.tts.constant.model.Entity;

import java.util.Arrays;
import java.util.List;

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

    public RequestBuilder insertValues(List<String> fieldNames){
        request.append("(");
        for (int fieldNumber = 0; fieldNumber < fieldNames.size(); fieldNumber++) {
            request.append("`")
                   .append(fieldNames.get(fieldNumber))
                   .append("`");
            if (fieldNumber != fieldNames.size() - 1){
                request.append(",");
            }
        }
        request.append(") VALUES (");
        for (int fieldNumber = 0; fieldNumber < fieldNames.size(); fieldNumber++) {
            request.append("?");
            if (fieldNumber != fieldNames.size() - 1){
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

    public RequestBuilder update(String tableName, List<String> fieldNames) {
        request.append("UPDATE ")
                .append(tableName)
                .append(" SET ");
        for (int fieldNumber = 0; fieldNumber < fieldNames.size(); fieldNumber++) {
            request.append(fieldNames.get(fieldNumber))
                   .append(" = ?");
            if (fieldNumber != fieldNames.size() - 1) {
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

    public RequestBuilder join(String tableName) {
        request.append(" JOIN ")
                .append(tableName)
                .append(" ");
        return this;
    }

    public RequestBuilder on(String columnName1, String columnName2) {
        request.append(" ON ")
                .append(columnName1)
                .append(" = ")
                .append(columnName2)
                .append(" ");
        return this;
    }

    public String build(){
        return request.toString();
    }
}
