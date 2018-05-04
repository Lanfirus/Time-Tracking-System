package ua.training.tts.constant.util;

/**
 * Holds statements to be used in utils/DBInitializator class to create/drop DBParameters with mock data
 */
public interface DBStatements {

    String CREATE_DB_STATEMENT = "CREATE SCHEMA IF NOT EXISTS time_tracking CHARACTER SET utf8 COLLATE utf8_general_ci";
    String DROP_DB_STATEMENT = "DROP SCHEMA IF EXISTS `time_tracking` ;";
    String CREATE_EMPLOYEE_TABLE_STATEMENT = "CREATE TABLE `employee` (" +
            "  `e_id` INT NOT NULL AUTO_INCREMENT," +
            "  `e_login` VARCHAR(45) NOT NULL," +
            "  `e_password` VARCHAR(45) NOT NULL," +
            "  `e_name` VARCHAR(45) NOT NULL," +
            "  `e_surname` VARCHAR(45) NOT NULL," +
            "  `e_patronymic` VARCHAR(45) NULL," +
            "  `e_email` VARCHAR(45) NOT NULL," +
            "  `e_mobile_phone` VARCHAR(45) NOT NULL," +
            "  `e_comment` VARCHAR(45) NULL," +
            "  `e_account_role` VARCHAR(45) NOT NULL DEFAULT 'employee'," +
            "  PRIMARY KEY (`e_id`)," +
            "  UNIQUE INDEX `e_id_UNIQUE` (`e_id` ASC), " +
            "  UNIQUE INDEX `e_login_UNIQUE` (`e_login` ASC))" +
            "  ENGINE = InnoDB;";
    String CREATE_PROJECT_TABLE_STATEMENT = "CREATE TABLE `project` (" +
            "  `p_id` INT NOT NULL AUTO_INCREMENT," +
            "  `p_name` VARCHAR(45) NOT NULL, " +
            "  `p_deadline` DATE NOT NULL," +
            "  `p_costcenter` INT NOT NULL," +
            "  `p_team_size` INT NULL DEFAULT 0," +
            "  `p_task_qty` INT NULL DEFAULT 0," +
            "  PRIMARY KEY  (`p_id`)," +
            "  UNIQUE INDEX `p_id_UNIQUE` (`p_id` ASC))" +
            "  ENGINE=InnoDB;";
    String CREATE_TASK_TABLE_STATEMENT = "CREATE TABLE `task` (" +
            "  `t_id` INT NOT NULL AUTO_INCREMENT," +
            "  `t_name` VARCHAR(45) NOT NULL," +
            "  `t_p_id` INT NOT NULL," +
            "  `t_status` ENUM('not_assigned', 'assigned', 'finished', 'expired', 'cancelled') NOT NULL DEFAULT 'not_assigned'," +
            "  `t_deadline` DATE NULL," +
            "  `t_spent_time` TIME NULL," +
            "  PRIMARY KEY (`t_id`)," +
            "  UNIQUE INDEX `t_id_UNIQUE` (`t_id` ASC)," +
            "  INDEX `fk_t_p_idx` (`t_p_id` ASC)," +
            "  CONSTRAINT `fk_t_p`" +
            "       FOREIGN KEY (`t_p_id`)" +
            "       REFERENCES `project` (`p_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION)" +
            "  ENGINE = InnoDB;";
    String CREATE_PROJECT_TEAM_TABLE_STATEMENT = "CREATE TABLE `project_team` (" +
            "  `pt_p_id` INT NOT NULL," +
            "  `pt_e_id` INT NOT NULL," +
            "  PRIMARY KEY (`pt_p_id`, `pt_e_id`)," +
            "  INDEX `fk_pt_p_idx` (`pt_p_id` ASC)," +
            "  INDEX `fk_pt_e_idx` (`pt_e_id` ASC)," +
            "  CONSTRAINT `fk_pt_p`" +
            "       FOREIGN KEY (`pt_p_id`)" +
            "       REFERENCES `project` (`p_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION," +
            "   CONSTRAINT `fk_pt_e`" +
            "       FOREIGN KEY (`pt_e_id`)" +
            "       REFERENCES `employee` (`e_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION)" +
            "  ENGINE = InnoDB;";
    String CREATE_TASK_TEAM_TABLE_STATEMENT = "CREATE TABLE `task_team` (" +
            "  `tt_t_id` INT NOT NULL," +
            "  `tt_e_id` INT NOT NULL," +
            "  PRIMARY KEY (`tt_t_id`)," +
            "  INDEX `fk_tt_t_idx` (`tt_t_id` ASC)," +
            "  INDEX `fk_tt_e_idx` (`tt_e_id` ASC)," +
            "  CONSTRAINT `fk_tt_t`" +
            "       FOREIGN KEY (`tt_t_id`)" +
            "       REFERENCES `task` (`t_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION," +
            "   CONSTRAINT `fk_tt_e`" +
            "       FOREIGN KEY (`tt_e_id`)" +
            "       REFERENCES `employee` (`e_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION)" +
            "  ENGINE = InnoDB;";
    String CREATE_EMPLOYEE_ARCHIVE_TABLE_STATEMENT = "CREATE TABLE `employee_archive` (" +
            "  `ea_id` INT NOT NULL AUTO_INCREMENT," +
            "  `ea_login` VARCHAR(45) NOT NULL," +
            "  `ea_password` VARCHAR(45) NOT NULL," +
            "  `ea_name` VARCHAR(45) NOT NULL," +
            "  `ea_surname` VARCHAR(45) NOT NULL," +
            "  `ea_patronymic` VARCHAR(45) NULL," +
            "  `ea_email` VARCHAR(45) NOT NULL," +
            "  `ea_mobile_phone` VARCHAR(45) NOT NULL," +
            "  `ea_comment` VARCHAR(45) NULL," +
            "  `ea_account_role` VARCHAR(45) NOT NULL DEFAULT 'employee'," +
            "  PRIMARY KEY (`ea_id`)," +
            "  UNIQUE INDEX `ea_id_UNIQUE` (`ea_id` ASC))" +
            "  ENGINE = InnoDB;";
    String CREATE_PROJECT_ARCHIVE_TABLE_STATEMENT = "CREATE TABLE `project_archive` (" +
            "  `pa_id` INT NOT NULL AUTO_INCREMENT," +
            "  `pa_name` VARCHAR(45) NOT NULL, " +
            "  `pa_deadline` DATE NOT NULL," +
            "  `pa_costcenter` INT NOT NULL," +
            "  `pa_team_size` INT NULL DEFAULT 0," +
            "  `pa_task_qty` INT NULL DEFAULT 0," +
            "  PRIMARY KEY  (`pa_id`)," +
            "  UNIQUE INDEX `pa_id_UNIQUE` (`pa_id` ASC))" +
            "  ENGINE=InnoDB;";
    String CREATE_TASK_ARCHIVE_TABLE_STATEMENT = "CREATE TABLE `task_archive` (" +
            "  `ta_id` INT NOT NULL AUTO_INCREMENT," +
            "  `ta_name` VARCHAR(45) NOT NULL," +
            "  `ta_pa_id` INT NOT NULL," +
            "  `ta_status` ENUM('not_assigned', 'assigned', 'finished', 'expired', 'cancelled') NOT NULL DEFAULT 'not_assigned'," +
            "  `ta_deadline` DATE NULL," +
            "  `ta_spent_time` TIME NULL," +
            "  PRIMARY KEY (`ta_id`)," +
            "  UNIQUE INDEX `ta_id_UNIQUE` (`ta_id` ASC)," +
            "  INDEX `fk_ta_pa_idx` (`ta_pa_id` ASC)," +
            "  CONSTRAINT `fk_ta_pa`" +
            "       FOREIGN KEY (`ta_pa_id`)" +
            "       REFERENCES `project_archive` (`pa_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION)" +
            "  ENGINE = InnoDB;";
    String CREATE_PROJECT_TEAM_ARCHIVE_TABLE_STATEMENT = "CREATE TABLE `project_team_archive` (" +
            "  `pta_pa_id` INT NOT NULL," +
            "  `pta_e_id` INT NOT NULL," +
            "  PRIMARY KEY (`pta_pa_id`, `pta_e_id`)," +
            "  INDEX `fk_pta_pa_idx` (`pta_pa_id` ASC)," +
            "  CONSTRAINT `fk_pta_pa`" +
            "       FOREIGN KEY (`pta_pa_id`)" +
            "       REFERENCES `project_archive` (`pa_id`)" +
            "       ON DELETE NO ACTION" +
            "       ON UPDATE NO ACTION)" +
            "  ENGINE = InnoDB;";

    String INSERT_EMPLOYEE_RECORD_STATEMENT = "INSERT INTO `employee` (`e_login`,`e_password`,`e_name`,`e_surname`," +
            "`e_patronymic`,`e_email`,`e_mobile_phone`,`e_comment`,`e_account_role`)";
    String INSERT_EMPLOYEE_RECORD1_VALUE = " VALUES ('aaa','47bce5c74f589f4867dbd57e9ca9f808','Petr','Petrov'," +
            "'Petrovich','petrovich@ua.ua','380501234567', 'Petrovich is the best!', 'admin');";
    String INSERT_EMPLOYEE_RECORD2_VALUE = " VALUES ('qqq','b2ca678b4c936f905fb82f2733f5297f','Ivan','Ivanov',''," +
            "'callToIvanych@i.ua','380661234567', 'just usual user', 'employee');";
    String INSERT_EMPLOYEE_RECORD3_VALUE = " VALUES ('111','698d51a19d8a121ce581499d7b701668','Maxim','Sidorov'," +
            "'Ivanovich','max@company.ua','380501234568', null, 'employee');";

    String INSERT_PROJECT_RECORD_STATEMENT = "INSERT INTO `project` (`p_name`,`p_deadline`,`p_costcenter`," +
            "  `p_team_size`,`p_task_qty`)";
    String INSERT_PROJECT_RECORD1_VALUE = " VALUES ('Cassandra','20180530','300123'," +
            "   '2', '1');";
    String INSERT_PROJECT_RECORD2_VALUE = " VALUES ('AX1267-2','20191222','12333'," +
            "   '2','2');";
    String INSERT_PROJECT_RECORD3_VALUE = " VALUES ('Project-X','20221012','100001'," +
            "   '1','2');";

    String INSERT_TASK_RECORD_STATEMENT = "INSERT INTO `task` (`t_name`,`t_p_id`,`t_status`," +
            "  `t_deadline`,`t_spent_time`)";
    String INSERT_TASK_RECORD1_VALUE = " VALUES ('Search for Cassandra','1','assigned'," +
            "   '20180528', '10:30');";
    String INSERT_TASK_RECORD2_VALUE = " VALUES ('AX1267-2-1','2','assigned'," +
            "   '20191101','2:00');";
    String INSERT_TASK_RECORD3_VALUE = " VALUES ('AX1267-2-2','2','assigned'," +
            "   null,'');";
    String INSERT_TASK_RECORD4_VALUE = " VALUES ('XXX1','3','assigned'," +
            "   null,'2:00');";
    String INSERT_TASK_RECORD5_VALUE = " VALUES ('XXX2','3','assigned'," +
            "   null,'');";

    String CREATE_TASK_DEADLINE_TRIGGER = "CREATE TRIGGER `task_BEFORE_INSERT` BEFORE INSERT ON `task` FOR EACH ROW begin\n" +
            "if new.t_deadline is null then\n" +
            "set new.t_deadline = (select p_deadline from project where project.p_id = new.t_p_id);\n" +
            "set new.t_name = upper(new.t_name);\n" +
            "end if;\n" +
            "end";
}
