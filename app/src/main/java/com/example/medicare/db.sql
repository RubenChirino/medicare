CREATE SCHEMA `hospital_system`;

## ==== USER TABLE ====
CREATE TABLE `hospital_system`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `address` VARCHAR(150) NULL,
  `gender` ENUM("MALE", "FEMALE") NULL DEFAULT NULL,
  `birth_date` DATE NULL,
  `role` ENUM("PATIENT", "DOCTOR", "ADMIN") NULL DEFAULT "PATIENT",
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);