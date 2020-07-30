-- MySQL Script generated by MySQL Workbench
-- Чт 16 июл 2020 15:04:18
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sportcity
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sportcity
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sportcity` ;
USE `sportcity` ;

-- -----------------------------------------------------
-- Table `sportcity`.`sportsman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`sportsman` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `name` VARCHAR(100) NOT NULL,
                                                       `club_name` VARCHAR(100) NOT NULL,
                                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`coach` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(100) NOT NULL,
                                                   `sport` ENUM('football', 'tennis', 'hockey', 'volleyball') NOT NULL,
                                                   PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`mentoring`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`mentoring` (
                                                       `sportsman_id` INT NOT NULL,
                                                       `coach_id` INT NOT NULL,
                                                       PRIMARY KEY (`sportsman_id`, `coach_id`),
                                                       INDEX `fk_mentoring_coach1_idx` (`coach_id` ASC) ,
                                                       CONSTRAINT `fk_mentoring_sportsman`
                                                           FOREIGN KEY (`sportsman_id`)
                                                               REFERENCES `sportcity`.`sportsman` (`id`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE CASCADE,
                                                       CONSTRAINT `fk_mentoring_coach1`
                                                           FOREIGN KEY (`coach_id`)
                                                               REFERENCES `sportcity`.`coach` (`id`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`abilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`abilities` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `sportsman_id` INT NOT NULL,
                                                       `category` INT NOT NULL,
                                                       `sport` ENUM('football', 'tennis', 'hockey', 'volleyball') NOT NULL,
                                                       PRIMARY KEY (`id`),
                                                       INDEX `fk_abilities_sportsman1_idx` (`sportsman_id` ASC) ,
                                                       CONSTRAINT `fk_abilities_sportsman1`
                                                           FOREIGN KEY (`sportsman_id`)
                                                               REFERENCES `sportcity`.`sportsman` (`id`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`competition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`competition` (
                                                         `id` INT NOT NULL AUTO_INCREMENT,
                                                         `name` VARCHAR(100) NOT NULL,
                                                         `c_date` DATE NOT NULL,
                                                         `sport` ENUM('football', 'tennis', 'hockey', 'volleyball') NOT NULL,
                                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`participation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`participation` (
                                                           `sportsman_id` INT NOT NULL AUTO_INCREMENT,
                                                           `competition_id` INT NOT NULL,
                                                           PRIMARY KEY (`sportsman_id`, `competition_id`),
                                                           INDEX `fk_participation_competition1_idx` (`competition_id` ASC) ,
                                                           CONSTRAINT `fk_participation_sportsman1`
                                                               FOREIGN KEY (`sportsman_id`)
                                                                   REFERENCES `sportcity`.`sportsman` (`id`)
                                                                   ON DELETE CASCADE
                                                                   ON UPDATE CASCADE,
                                                           CONSTRAINT `fk_participation_competition1`
                                                               FOREIGN KEY (`competition_id`)
                                                                   REFERENCES `sportcity`.`competition` (`id`)
                                                                   ON DELETE CASCADE
                                                                   ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`organizer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`organizer` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `name` VARCHAR(100) NOT NULL,
                                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`arrangement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`arrangement` (
                                                         `competition_id` INT NOT NULL,
                                                         `organizer_id` INT NOT NULL,
                                                         PRIMARY KEY (`competition_id`, `organizer_id`),
                                                         INDEX `fk_arrangement_organizer1_idx` (`organizer_id` ASC) ,
                                                         CONSTRAINT `fk_arrangement_competition1`
                                                             FOREIGN KEY (`competition_id`)
                                                                 REFERENCES `sportcity`.`competition` (`id`)
                                                                 ON DELETE CASCADE
                                                                 ON UPDATE CASCADE,
                                                         CONSTRAINT `fk_arrangement_organizer1`
                                                             FOREIGN KEY (`organizer_id`)
                                                                 REFERENCES `sportcity`.`organizer` (`id`)
                                                                 ON DELETE CASCADE
                                                                 ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`sport_facilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`sport_facilities` (
                                                              `id` INT NOT NULL,
                                                              PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`location` (
                                                      `competition_id` INT NOT NULL,
                                                      `sport_facilities_id` INT NOT NULL,
                                                      PRIMARY KEY (`competition_id`, `sport_facilities_id`),
                                                      INDEX `fk_location_sport_facilities1_idx` (`sport_facilities_id` ASC) ,
                                                      CONSTRAINT `fk_location_competition1`
                                                          FOREIGN KEY (`competition_id`)
                                                              REFERENCES `sportcity`.`competition` (`id`)
                                                              ON DELETE CASCADE
                                                              ON UPDATE CASCADE,
                                                      CONSTRAINT `fk_location_sport_facilities1`
                                                          FOREIGN KEY (`sport_facilities_id`)
                                                              REFERENCES `sportcity`.`sport_facilities` (`id`)
                                                              ON DELETE CASCADE
                                                              ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`ice_arena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`ice_arena` (
                                                       `id` INT NOT NULL,
                                                       `square` INT NOT NULL,
                                                       PRIMARY KEY (`id`),
                                                       CONSTRAINT `fk_ice_arena_sport_facilities1`
                                                           FOREIGN KEY (`id`)
                                                               REFERENCES `sportcity`.`sport_facilities` (`id`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`stadium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`stadium` (
                                                     `id` INT NOT NULL,
                                                     `capacity` INT NOT NULL,
                                                     PRIMARY KEY (`id`),
                                                     CONSTRAINT `fk_stadium_sport_facilities1`
                                                         FOREIGN KEY (`id`)
                                                             REFERENCES `sportcity`.`sport_facilities` (`id`)
                                                             ON DELETE CASCADE
                                                             ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`volleyball_arena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`volleyball_arena` (
                                                              `id` INT NOT NULL,
                                                              `net_height` INT NOT NULL,
                                                              `net_width` INT NOT NULL,
                                                              PRIMARY KEY (`id`),
                                                              CONSTRAINT `fk_volleyball_arena_sport_facilities1`
                                                                  FOREIGN KEY (`id`)
                                                                      REFERENCES `sportcity`.`sport_facilities` (`id`)
                                                                      ON DELETE CASCADE
                                                                      ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sportcity`.`court`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sportcity`.`court` (
                                                   `id` INT NOT NULL,
                                                   `coverage_type` ENUM('grass', 'clay') NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   CONSTRAINT `fk_court_sport_facilities1`
                                                       FOREIGN KEY (`id`)
                                                           REFERENCES `sportcity`.`sport_facilities` (`id`)
                                                           ON DELETE CASCADE
                                                           ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;