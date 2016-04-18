create database WerewolvesAndWanderer;

CREATE TABLE `WerewolvesAndWanderer`.`players` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `strength` INT NULL,
  `wealth` INT NULL,
  `monster_tally` INT NULL,
  `current_room` INT NULL,
  PRIMARY KEY (`id`));
