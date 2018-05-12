package ua.training.tts.constant.model;

public interface Entity {

    String EMPLOYEE_ID_COLUMN_NAME = "Id";
    String EMPLOYEE_LOGIN_COLUMN_NAME = "Login";
    String EMPLOYEE_PASSWORD_COLUMN_NAME = "Password";
    String EMPLOYEE_NAME_COLUMN_NAME = "Name";
    String EMPLOYEE_SURNAME_COLUMN_NAME = "Surname";
    String EMPLOYEE_PATRONYMIC_COLUMN_NAME = "Patronymic";
    String EMPLOYEE_EMAIL_COLUMN_NAME = "Email";
    String EMPLOYEE_MOBILE_PHONE_COLUMN_NAME = "Mobile phone";
    String EMPLOYEE_COMMENT_COLUMN_NAME = "Comment";
    String EMPLOYEE_ACCOUNT_ROLE_COLUMN_NAME = "Account role";
    String EMPLOYEE_OLD_LOGIN_COLUMN_NAME = "Old login";

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

    String PROJECT_ID = "Id";
    String PROJECT_NAME = "Name";
    String PROJECT_DEADLINE = "Deadline";
    String PROJECT_COSTCENTER = "Cost center";
    String PROJECT_TEAM_SIZE = "Team size";
    String PROJECT_TASK_QTY = "Task qty";

    String TASK_ID = "t_id";
    String TASK_NAME = "t_name";
    String TASK_PROJECT_ID = "t_p_id";
    String TASK_STATUS = "t_status";
    String TASK_DEADLINE = "t_deadline";
    String TASK_SPENT_TIME = "t_spent_time";

    String ACCOUNT_ROLE_ADMIN = "admin";
    String ACCOUNT_ROLE_EMPLOYEE = "employee";
    String ACCOUNT_ROLE_UNKNOWN = "unknown";
}
