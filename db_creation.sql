-- MySQL Script generated by MySQL Workbench
-- Fri May 18 20:53:58 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema time_tracking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema time_tracking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `time_tracking` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `time_tracking` ;

-- -----------------------------------------------------
-- Table `time_tracking`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NOT NULL,
  `project_status` ENUM('new', 'assigned', 'fiinished', 'cancelled') NOT NULL DEFAULT 'new',
  `project_deadline` DATE NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE INDEX `task_id_UNIQUE` (`project_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_tracking`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `employee_login` VARCHAR(20) NOT NULL,
  `employee_password` VARCHAR(32) NOT NULL,
  `employee_name` VARCHAR(45) NOT NULL,
  `employee_surname` VARCHAR(45) NOT NULL,
  `employee_patronymic` VARCHAR(45) NOT NULL DEFAULT '',
  `employee_email` VARCHAR(319) NOT NULL,
  `employee_mobile_phone` VARCHAR(13) NOT NULL,
  `employee_comment` VARCHAR(45) NOT NULL DEFAULT '',
  `employee_account_role` ENUM('admin', 'employee') NOT NULL DEFAULT 'employee',
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`employee_login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_tracking`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking`.`task` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `project_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `task_name` VARCHAR(45) NOT NULL,
  `task_status` ENUM('assigned', 'finished', 'cancelled') NOT NULL DEFAULT 'assigned',
  `task_deadline` DATE NOT NULL,
  `task_spent_time` INT NOT NULL DEFAULT 0,
  `task_approval_state` ENUM('approved', 'not_approved', 'new_request') NOT NULL DEFAULT 'not_approved',
  PRIMARY KEY (`task_id`, `project_id`, `employee_id`),
  INDEX `fk_task_employee_idx` (`employee_id` ASC),
  INDEX `fk_task_project_idx` (`project_id` ASC),
  UNIQUE INDEX `task_id_UNIQUE` (`task_id` ASC),
  CONSTRAINT `fk_task_project`
    FOREIGN KEY (`project_id`)
    REFERENCES `time_tracking`.`project` (`project_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `time_tracking`.`employee` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_tracking`.`project_archive`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking`.`project_archive` (
  `project_id` INT NOT NULL,
  `project_name` VARCHAR(45) NOT NULL,
  `project_status` ENUM('new', 'assigned', 'fiinished', 'cancelled') NOT NULL DEFAULT 'new',
  `project_deadline` DATE NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE INDEX `task_id_UNIQUE` (`project_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_tracking`.`task_archive`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking`.`task_archive` (
  `task_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `task_name` VARCHAR(45) NOT NULL,
  `task_status` ENUM('assigned', 'finished', 'cancelled') NOT NULL DEFAULT 'assigned',
  `task_deadline` DATE NOT NULL,
  `task_spent_time` INT NOT NULL DEFAULT 0,
  `task_approval_state` ENUM('approved', 'not_approved', 'new_request') NOT NULL DEFAULT 'not_approved',
  PRIMARY KEY (`task_id`, `project_id`, `employee_id`),
  INDEX `fk_task_employee_idx` (`employee_id` ASC),
  INDEX `fk_task_project_idx` (`project_id` ASC),
  UNIQUE INDEX `task_id_UNIQUE` (`task_id` ASC),
  CONSTRAINT `fk_task_project0`
    FOREIGN KEY (`project_id`)
    REFERENCES `time_tracking`.`project_archive` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_employee0`
    FOREIGN KEY (`employee_id`)
    REFERENCES `time_tracking`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
