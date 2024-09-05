-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vegesfood
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vegesfood
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vegesfood` DEFAULT CHARACTER SET utf8 ;
USE `vegesfood` ;

-- -----------------------------------------------------
-- Table `vegesfood`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
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
-- Table `vegesfood`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`idorder`),
  INDEX `fk_order_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `vegesfood`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`category` (
  `idcategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`product` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `quantity` DECIMAL NULL,
  `price` DECIMAL NULL,
  `img` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  `idcategory` INT NOT NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_product_category1_idx` (`idcategory` ASC) VISIBLE,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`idcategory`)
    REFERENCES `vegesfood`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`orderItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`orderItems` (
  `idorder` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `quantity` INT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`idorder`, `idproduct`),
  INDEX `fk_order_has_product_product1_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`idorder` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`idorder`)
    REFERENCES `vegesfood`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`idproduct`)
    REFERENCES `vegesfood`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`admin` (
  `idadmin` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idadmin`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`userInterests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`userInterests` (
  `iduser` INT NOT NULL,
  `idcategory` INT NOT NULL,
  PRIMARY KEY (`iduser`, `idcategory`),
  INDEX `fk_user_has_category_category1_idx` (`idcategory` ASC) VISIBLE,
  INDEX `fk_user_has_category_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_category_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `vegesfood`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_category_category1`
    FOREIGN KEY (`idcategory`)
    REFERENCES `vegesfood`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`cartItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`cartItems` (
  `idproduct` INT NOT NULL,
  `quantity` INT NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`idproduct`, `iduser`),
  INDEX `fk_user_has_product_product1_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_cartItems_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_product1`
    FOREIGN KEY (`idproduct`)
    REFERENCES `vegesfood`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartItems_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `vegesfood`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vegesfood`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vegesfood`.`wishlist` (
  `idproduct` INT NOT NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`idproduct`, `iduser`),
  INDEX `fk_user_has_product_product2_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_wishlist_user1_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_product2`
    FOREIGN KEY (`idproduct`)
    REFERENCES `vegesfood`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wishlist_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `vegesfood`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
