package ua.training.tts.constant.model;

public interface Entity {

    String EMPLOYEE_ID = "id";
    String EMPLOYEE_LOGIN = "login";
    String EMPLOYEE_PASSWORD = "password";
    String EMPLOYEE_NAME = "name";
    String EMPLOYEE_SURNAME = "surname";
    String EMPLOYEE_PATRONYMIC = "patronymic";
    String EMPLOYEE_EMAIL = "email";
    String EMPLOYEE_MOBILE_PHONE = "mobilePhone";
    String EMPLOYEE_COMMENT = "comment";
    String EMPLOYEE_ACCOUNT_ROLE = "accountRole";

    String PROJECT_ID = "id";
    String PROJECT_NAME = "name";
    String PROJECT_DEADLINE = "deadline";
    String PROJECT_COSTCENTER = "costCenter";
    String PROJECT_TEAM_SIZE = "team_size";
    String PROJECT_TASK_QTY = "taskQty";

    String TASK_ID = "id";
    String TASK_NAME = "name";
    String TASK_PROJECT_ID = "projectID";
    String TASK_STATUS = "status";
    String TASK_DEADLINE = "deadline";
    String TASK_SPENT_TIME = "spentTime";
}
