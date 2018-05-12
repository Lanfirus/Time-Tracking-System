package ua.training.tts.constant.model.dao;

public interface TableParameters {

    String EMPLOYEE_TABLE_NAME = "employee";
    String PROJECT_TABLE_NAME = "project";
    String TASK_TABLE_NAME = "task";
    String TASK_TEAM_TABLE_NAME = "task_team";

    String EMPLOYEE_ID = "employee_id";
    String EMPLOYEE_LOGIN = "login";
    String EMPLOYEE_PASSWORD = "password";
    String EMPLOYEE_NAME = "name";
    String EMPLOYEE_SURNAME = "surname";
    String EMPLOYEE_PATRONYMIC = "patronymic";
    String EMPLOYEE_EMAIL = "email";
    String EMPLOYEE_MOBILE_PHONE = "mobile_phone";
    String EMPLOYEE_COMMENT = "comment";
    String EMPLOYEE_ACCOUNT_ROLE = "account_role";

    String TASK_ID = "task_id";
    String TASK_PROJECT_ID = "project_id";
    String TASK_EMPLOYEE_ID = "employee_id";
    String TASK_NAME = "name";
    String TASK_STATUS = "status";
    String TASK_DEADLINE = "deadline";
    String TASK_SPENT_TIME = "spent_time";
    String TASK_APPROVED = "approved";

    String PROJECT_ID = "project_id";
    String PROJECT_NAME = "name";
    String PROJECT_DEADLINE = "deadline";
    String PROJECT_STATUS = "status";

    String TASK_TEAM_TASK_ID = "tt_t_id";
    String TASK_TEAM_ENPLOYEE_ID = "tt_e_id";
}
