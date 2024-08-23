-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8 ;
USE `db` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `creditLimit` INT NULL,
  `job` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `idUser_UNIQUE` (`userId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`product` (
  `productId` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `quantity` INT NULL,
  `price` INT NULL,
  PRIMARY KEY (`productId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`order` (
  `orderId` INT NOT NULL,
  `date` DATE NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`orderId`, `userId`),
  INDEX `fk_order_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_order_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `db`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`category` (
  `categoryId` INT NOT NULL,
  `categoryName` VARCHAR(45) NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Interests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`Interests` (
  `interestsId` INT NOT NULL,
  `interestName` VARCHAR(45) NULL,
  PRIMARY KEY (`interestsId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`admin` (
  `adminId` INT NOT NULL,
  `adminUserName` VARCHAR(45) NULL,
  `adminPassword` VARCHAR(45) NULL,
  PRIMARY KEY (`adminId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`cart` (
  `cartId` INT NOT NULL,
  `date` DATE NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`cartId`, `userId`),
  INDEX `fk_cart_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_cart_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `db`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orderProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`orderProduct` (
  `orderId` INT NOT NULL,
  `userId` INT NOT NULL,
  `productId` INT NOT NULL,
  PRIMARY KEY (`orderId`, `userId`, `productId`),
  INDEX `fk_order_has_product_product1_idx` (`productId` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`orderId` ASC, `userId` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`orderId` , `userId`)
    REFERENCES `db`.`order` (`orderId` , `userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`productId`)
    REFERENCES `db`.`product` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cartProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`cartProduct` (
  `cartId` INT NOT NULL,
  `userId` INT NOT NULL,
  `productId` INT NOT NULL,
  PRIMARY KEY (`cartId`, `userId`, `productId`),
  INDEX `fk_cart_has_product_product1_idx` (`productId` ASC) VISIBLE,
  INDEX `fk_cart_has_product_cart1_idx` (`cartId` ASC, `userId` ASC) VISIBLE,
  CONSTRAINT `fk_cart_has_product_cart1`
    FOREIGN KEY (`cartId` , `userId`)
    REFERENCES `db`.`cart` (`cartId` , `userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_has_product_product1`
    FOREIGN KEY (`productId`)
    REFERENCES `db`.`product` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`userInterests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`userInterests` (
  `interestsId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`interestsId`, `userId`),
  INDEX `fk_Interests_has_User_User1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_Interests_has_User_Interests1_idx` (`interestsId` ASC) VISIBLE,
  CONSTRAINT `fk_Interests_has_User_Interests1`
    FOREIGN KEY (`interestsId`)
    REFERENCES `db`.`Interests` (`interestsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Interests_has_User_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `db`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productCategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`productCategory` (
  `productId` INT NOT NULL,
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`productId`, `categoryId`),
  INDEX `fk_product_has_category_category1_idx` (`categoryId` ASC) VISIBLE,
  INDEX `fk_product_has_category_product1_idx` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_product_has_category_product1`
    FOREIGN KEY (`productId`)
    REFERENCES `db`.`product` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_category_category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `db`.`category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
