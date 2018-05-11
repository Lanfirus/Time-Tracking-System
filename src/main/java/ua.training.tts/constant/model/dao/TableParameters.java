package ua.training.tts.constant.model.dao;

public interface TableParameters {

    String EMPLOYEE_TABLE_NAME = "employee";
    String PROJECT_TABLE_NAME = "project";
    String TASK_TABLE_NAME = "task";
    String TASK_TEAM_TABLE_NAME = "task_team";

    String EMPLOYEE_ID = "e_id";
    String EMPLOYEE_LOGIN = "e_login";
    String EMPLOYEE_PASSWORD = "e_password";
    String EMPLOYEE_NAME = "e_name";
    String EMPLOYEE_SURNAME = "e_surname";
    String EMPLOYEE_PATRONYMIC = "e_patronymic";
    String EMPLOYEE_EMAIL = "e_email";
    String EMPLOYEE_MOBILE_PHONE = "e_mobile_phone";
    String EMPLOYEE_COMMENT = "e_comment";
    String EMPLOYEE_ACCOUNT_ROLE = "e_account_role";

    String TASK_ID = "t_id";
    String TASK_NAME = "t_name";
    String TASK_PROJECT_ID = "t_p_id";
    String TASK_STATUS = "t_status";
    String TASK_DEADLINE = "t_deadline";
    String TASK_SPENT_TIME = "t_spent_time";

    String TASK_TEAM_TASK_ID = "tt_t_id";
    String TASK_TEAM_ENPLOYEE_ID = "tt_e_id";
}
