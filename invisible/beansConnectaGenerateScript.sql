-- MySQL Script generated by MySQL Workbench
-- Wed Jun 26 15:00:18 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema beansconnecta
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `beansconnecta` ;

-- -----------------------------------------------------
-- Schema beansconnecta
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `beansconnecta` DEFAULT CHARACTER SET utf8 ;
USE `beansconnecta` ;

-- -----------------------------------------------------
-- Table `beansconnecta`.`companies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`companies` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`companies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `scale` VARCHAR(255) NOT NULL DEFAULT '0-100',
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `beansconnecta`.`jobTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`jobTypes` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`jobTypes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `beansconnecta`.`jobs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`jobs` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`jobs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `requirement` TEXT NOT NULL,
  `tag` ENUM('FULLTIME', 'PARTIME', 'INTERN', 'FREELANCE') NOT NULL DEFAULT 'FULLTIME',
  `minPay` DECIMAL(13,2) NOT NULL DEFAULT 400,
  `maxPay` DECIMAL(13,2) NOT NULL DEFAULT 500,
  `isActive` TINYINT NULL DEFAULT 1,
  `quantity` SMALLINT NULL DEFAULT 1,
  `createTime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NULL,
  `companyId` INT NOT NULL,
  `jobTypeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_company1_idx` (`companyId` ASC) VISIBLE,
  INDEX `fk_job_jobType1_idx` (`jobTypeId` ASC) VISIBLE,
  CONSTRAINT `fk_job_company1`
    FOREIGN KEY (`companyId`)
    REFERENCES `beansconnecta`.`companies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_jobType1`
    FOREIGN KEY (`jobTypeId`)
    REFERENCES `beansconnecta`.`jobTypes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `beansconnecta`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`users` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(15) NULL,
  `password` TEXT NULL,
  `role` ENUM('COMPANY', 'CANDIDATE') NOT NULL DEFAULT 'CANDIDATE',
  `companyId` INT NULL,
  `createTime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NULL,
  `resetToken` VARCHAR(255) NULL,
  `resetCreatedAt` TIMESTAMP NULL,
  `rememberToken` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_users_companies1_idx` (`companyId` ASC) VISIBLE,
  CONSTRAINT `fk_users_companies1`
    FOREIGN KEY (`companyId`)
    REFERENCES `beansconnecta`.`companies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `beansconnecta`.`candidates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`candidates` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`candidates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `dob` DATE NOT NULL,
  `gender` TINYINT NOT NULL DEFAULT 1,
  `image` BLOB NULL,
  `city` VARCHAR(45) NULL,
  `address` TEXT NULL,
  `role` VARCHAR(255) NULL DEFAULT 'Software Engineer',
  `objective` TEXT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_candidate_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_candidate_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `beansconnecta`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `beansconnecta`.`applies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`applies` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`applies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `coverLetter` TEXT NOT NULL,
  `status` ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED') NULL DEFAULT 'PENDING',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  `jobId` INT NOT NULL,
  `candidateId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_job1_idx` (`jobId` ASC) VISIBLE,
  INDEX `fk_activity_candidate1_idx` (`candidateId` ASC) VISIBLE,
  CONSTRAINT `fk_activity_job1`
    FOREIGN KEY (`jobId`)
    REFERENCES `beansconnecta`.`jobs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_candidate1`
    FOREIGN KEY (`candidateId`)
    REFERENCES `beansconnecta`.`candidates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `beansconnecta`.`skills`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`skills` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`skills` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tag` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `candidateId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_skillSet_candidate1_idx` (`candidateId` ASC) VISIBLE,
  CONSTRAINT `fk_skillSet_candidate1`
    FOREIGN KEY (`candidateId`)
    REFERENCES `beansconnecta`.`candidates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `beansconnecta`.`experiences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`experiences` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`experiences` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `position` VARCHAR(255) NOT NULL,
  `companyName` VARCHAR(255) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `detail` TEXT NULL,
  `candidateId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_experience_candidate1_idx` (`candidateId` ASC) VISIBLE,
  CONSTRAINT `fk_experience_candidate1`
    FOREIGN KEY (`candidateId`)
    REFERENCES `beansconnecta`.`candidates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `beansconnecta`.`companyImages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beansconnecta`.`companyImages` ;

CREATE TABLE IF NOT EXISTS `beansconnecta`.`companyImages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image` BLOB NULL,
  `companyId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_companyImage_company1_idx` (`companyId` ASC) VISIBLE,
  CONSTRAINT `fk_companyImage_company1`
    FOREIGN KEY (`companyId`)
    REFERENCES `beansconnecta`.`companies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
