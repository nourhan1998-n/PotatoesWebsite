-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema potatoes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema potatoes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `potatoes` DEFAULT CHARACTER SET utf8 ;
USE `potatoes` ;

-- -----------------------------------------------------
-- Table `potatoes`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`user` (
  `iduser` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `job` VARCHAR(45) NULL,
  `credit` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`order` (
  `idorder` INT NOT NULL,
  `date` DATE NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`idorder`),
  INDEX `fk_order_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `potatoes`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`category` (
  `idcategory` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`product` (
  `idproduct` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `quantity` VARCHAR(45) NULL,
  `price` DECIMAL NULL,
  `img` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `cc` INT NULL,
  `description` VARCHAR(45) NULL,
  `model` INT NULL,
  `idcategory` INT NOT NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_product_category1_idx` (`idcategory` ASC) VISIBLE,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`idcategory`)
    REFERENCES `potatoes`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`orderItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`orderItems` (
  `idorder` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `quantity` INT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`idorder`, `idproduct`),
  INDEX `fk_order_has_product_product1_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`idorder` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`idorder`)
    REFERENCES `potatoes`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`idproduct`)
    REFERENCES `potatoes`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`admin` (
  `idadmin` INT NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idadmin`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`cartItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`cartItems` (
  `iduser` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`iduser`, `idproduct`),
  INDEX `fk_user_has_product_product1_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_user_has_product_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `potatoes`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product_product1`
    FOREIGN KEY (`idproduct`)
    REFERENCES `potatoes`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`userInterests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`userInterests` (
  `iduser` INT NOT NULL,
  `idcategory` INT NOT NULL,
  PRIMARY KEY (`iduser`, `idcategory`),
  INDEX `fk_user_has_category_category1_idx` (`idcategory` ASC) VISIBLE,
  INDEX `fk_user_has_category_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_category_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `potatoes`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_category_category1`
    FOREIGN KEY (`idcategory`)
    REFERENCES `potatoes`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potatoes`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `potatoes`.`wishlist` (
  `iduser` INT NOT NULL,
  `idproduct` INT NOT NULL,
  PRIMARY KEY (`iduser`, `idproduct`),
  INDEX `fk_user_has_product_product2_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_user_has_product_user2_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_user2`
    FOREIGN KEY (`iduser`)
    REFERENCES `potatoes`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product_product2`
    FOREIGN KEY (`idproduct`)
    REFERENCES `potatoes`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
