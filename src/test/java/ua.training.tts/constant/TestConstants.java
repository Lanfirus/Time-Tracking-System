package ua.training.tts.constant;

public interface TestConstants {

    String TABLE = "table";
    String COLUMN = "column";
    String VALUE1 = "value1";
    String VALUE2 = "value2";


    String INSERT_INTO_TABLE_EXPECTED = "INSERT INTO `TABLE` ";
    String INSERT_VALUES_EXPECTED = "(`value1`,`value2`) VALUES (?,?)";
    String SELECT_ALL_FROM_TABLE_EXPECTED = "select * from table";
    String WHERE_EXPECTED = " where column = ?";
    String AND_EXPECTED = " and column = ?";
    String UPDATE_EXPECTED = "update table set value1 = ?,value2 = ? ";
    String DELETE = "delete from column ";
}
