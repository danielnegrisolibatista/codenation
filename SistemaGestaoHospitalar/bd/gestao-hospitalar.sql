-- MySQL Script generated by MySQL Workbench
-- Wed Mar 20 00:41:28 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestao_hospitalar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestao_hospitalar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestao_hospitalar` DEFAULT CHARACTER SET utf8 ;
USE `gestao_hospitalar` ;

-- -----------------------------------------------------
-- Table `gestao_hospitalar`.`hospital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_hospitalar`.`hospital` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `beds` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_hospitalar`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_hospitalar`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `cpf` VARCHAR(255) NULL,
  `birth_date` DATE NULL,
  `checkin_date` DATE NULL,
  `checkout_date` DATE NULL,
  `address` VARCHAR(255) NULL,
  `sex` VARCHAR(50) NULL,
  `hospital_id` INT NOT NULL,
  PRIMARY KEY (`id`, `hospital_id`),
  INDEX `fk_patient_hospital_idx` (`hospital_id` ASC) VISIBLE,
  CONSTRAINT `fk_patient_hospital`
    FOREIGN KEY (`hospital_id`)
    REFERENCES `gestao_hospitalar`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_hospitalar`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_hospitalar`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(1024) NULL,
  `type` VARCHAR(60) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_hospitalar`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_hospitalar`.`stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` FLOAT NULL,
  `unity` VARCHAR(60) NULL,
  `product_id` INT NOT NULL,
  `hospital_id` INT NOT NULL,
  PRIMARY KEY (`id`, `product_id`, `hospital_id`),
  INDEX `fk_stock_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_stock_hospital1_idx` (`hospital_id` ASC) VISIBLE,
  CONSTRAINT `fk_stock_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `gestao_hospitalar`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stock_hospital1`
    FOREIGN KEY (`hospital_id`)
    REFERENCES `gestao_hospitalar`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
