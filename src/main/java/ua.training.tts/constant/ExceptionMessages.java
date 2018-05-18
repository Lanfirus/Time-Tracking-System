package ua.training.tts.constant;

public interface ExceptionMessages {

    String CONNECTION_PROBLEM = "There is some problem with connection to database. Try again later on.";
    String DAO_CREATION_PROBLEM = "There is some problem with DAO creation. Try again later on,";
    String SQL_GENERAL_PROBLEM = "There is some problem with interaction with SQL. Try again later on";
    String UNIQUE = "UNIQUE";
    String EMPTY_RESULT_SET = "empty result set";
    String NOT_UNIQUE_LOGIN = "Not unique login";
    String CANNOT_ADD_UPDATE_CHILD_ROW = "Cannot add or update a child row";
    String BAD_REGISTRATION_DATA = "Not correct registration data";
    String ILLEGAL_OPERATION_ON_EMPTY_RESULT_SET = "Illegal operation on empty result set.";
}
