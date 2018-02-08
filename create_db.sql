-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema audio_portal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema audio_portal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audio_portal` DEFAULT CHARACTER SET utf8 ;
USE `audio_portal` ;

-- -----------------------------------------------------
-- Table `audio_portal`.`album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`album` (
  `idAlbum` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ таблицы. Определяет id альбома',
  `album_name` VARCHAR(255) NOT NULL COMMENT 'Название альбома. Так же является индексом, для более быстрого поиска названия альбома',
  `studio` VARCHAR(255) NOT NULL COMMENT 'Студия, где был записан альбом',
  `date` DATE NOT NULL COMMENT 'Дата выпуска альбома\n',
  PRIMARY KEY (`idAlbum`),
  INDEX `album_name_INDEX` (`album_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = 'Администратор добавляет новые альбомы. Альбомы характеризуются названием, студией ( где альбом был записан), и датой выпуска.';


-- -----------------------------------------------------
-- Table `audio_portal`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`genre` (
  `idGenre` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ таблицы. Определяет id жанра.',
  `genre` VARCHAR(255) NOT NULL COMMENT 'Наименование жанра.',
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = 'Администратор добавляет жанры песен';


-- -----------------------------------------------------
-- Table `audio_portal`.`audio_track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`audio_track` (
  `idAudio_Track` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ таблицы. Определяет id трека.',
  `name` VARCHAR(255) NOT NULL COMMENT 'Наименование аудио трека. Так же является индексом, для более быстрого поиска названия песни.',
  `artist` VARCHAR(255) NOT NULL COMMENT 'Наименование исполнителя. Так же является индексом, для более быстрого поиска исполнителя',
  `idAlbum` INT(11) NOT NULL COMMENT 'По этому ключу можно получить информацию об альбоме(название альбома, студия(где записывался), дата выхода)',
  `idGenre` INT(11) NOT NULL COMMENT 'По этому ключу можно получить информацию об жанре',
  `price` DECIMAL(10,0) NOT NULL COMMENT 'Цена за один аудио трек',
  `link` VARCHAR(255) NOT NULL COMMENT 'Ссылка на аудио файл',
  `image_link` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idAudio_Track`),
  INDEX `fk_Audio_Tracks_Albums1_idx` (`idAlbum` ASC),
  INDEX `fk_Audio_Track_Genres1_idx` (`idGenre` ASC),
  INDEX `name_INDEX` (`name` ASC),
  INDEX `artist_INDEX` (`artist` ASC),
  CONSTRAINT `fk_Audio_Track_Genres1`
    FOREIGN KEY (`idGenre`)
    REFERENCES `audio_portal`.`genre` (`idGenre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Audio_Tracks_Albums1`
    FOREIGN KEY (`idAlbum`)
    REFERENCES `audio_portal`.`album` (`idAlbum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8
COMMENT = 'Администратор может добавить новые аудио треки. Аудио трек характеризуется именем песни, именем исполнителя, альбомом, жанром и ценой. Каждая песня будет содержать ссылку.';


-- -----------------------------------------------------
-- Table `audio_portal`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`user` (
  `login` VARCHAR(255) NOT NULL COMMENT 'Первичный ключ таблицы. Определяет логин пользователя',
  `password` VARCHAR(255) NOT NULL COMMENT 'Пароль пользователя',
  `email` VARCHAR(45) NOT NULL COMMENT 'Email пользователя',
  `role` VARCHAR(45) NOT NULL COMMENT 'Роль пользователя. Определяет в системе администратора и клиента. Так же является индексом, для более быстрого поиска администраторов или клиентов',
  `bonus` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`login`),
  INDEX `role_INDEX` (`role` ASC),
  INDEX `bonus_INDEX` (`bonus` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, хранящая данные о пользователях. Каждый пользователь имеет логин и пароль. Так же столбец \"role\" позволяет разграничить администратора и клиента. Столбец \"email\", хранит email адреса, по которым можно производить рассылку. Столбец \"bonus\", предоставляет возможность устанавливать администратором различные бонусы для клиентов.';


-- -----------------------------------------------------
-- Table `audio_portal`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`comment` (
  `idComments` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ таблицы. Определяет id комментария.',
  `idAudio_Track` INT(11) NOT NULL COMMENT 'По этому ключу можно получить информацию о треке, по которому будет писаться комментарий.',
  `login` VARCHAR(255) NOT NULL COMMENT 'По этому ключу можно получить информацию о пользователе, который будет писать комментарий',
  `text` VARCHAR(255) NOT NULL COMMENT 'Текст комментария',
  `date` DATE NOT NULL COMMENT 'Дата, когда написан комментарий',
  PRIMARY KEY (`idComments`),
  INDEX `fk_Comments_Audio_Track1_idx` (`idAudio_Track` ASC),
  INDEX `fk_Comments_User1_idx` (`login` ASC),
  CONSTRAINT `fk_Comments_Audio_Track1`
    FOREIGN KEY (`idAudio_Track`)
    REFERENCES `audio_portal`.`audio_track` (`idAudio_Track`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_User1`
    FOREIGN KEY (`login`)
    REFERENCES `audio_portal`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8
COMMENT = 'Пользователь может оставить множество комментариев о песней';


-- -----------------------------------------------------
-- Table `audio_portal`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`order` (
  `idOrder` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ таблицы. Определяет id заказа.',
  `login` VARCHAR(255) NOT NULL COMMENT 'По этому ключу можно получить информацию о пользователе, который будет делать заказ',
  `card_number` VARCHAR(255) NOT NULL COMMENT 'Номер банковской карты',
  `svc_code` VARCHAR(255) NULL DEFAULT NULL,
  `date` DATE NOT NULL COMMENT 'Дата, когда будет сделан заказ. Так же является индексом, для более быстрого поиска заказов по определенной дате',
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Order_Users_idx` (`login` ASC),
  INDEX `date_INDEX` (`date` ASC),
  CONSTRAINT `fk_Order_Users`
    FOREIGN KEY (`login`)
    REFERENCES `audio_portal`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, хранящая историю заказов клиентов. Таблица содержит информацию: логин, данные банковской карты, дата покупки';


-- -----------------------------------------------------
-- Table `audio_portal`.`order_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_portal`.`order_list` (
  `idOrder` INT(11) NOT NULL COMMENT 'По этому ключу можно получить информацию о заказе, который сделан пользователем',
  `idAudio_Track` INT(11) NOT NULL COMMENT 'По этому ключу можно получить информацию о аудио треке, который заказан',
  PRIMARY KEY (`idOrder`, `idAudio_Track`),
  INDEX `fk_Audio_Track_has_Order_Order1_idx` (`idOrder` ASC),
  INDEX `fk_Audio_Track_has_Order_Audio_Track1_idx` (`idAudio_Track` ASC),
  CONSTRAINT `fk_Audio_Track_has_Order_Audio_Track1`
    FOREIGN KEY (`idAudio_Track`)
    REFERENCES `audio_portal`.`audio_track` (`idAudio_Track`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Audio_Track_has_Order_Order1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `audio_portal`.`order` (`idOrder`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, позволяющая группировать заказы клиентов музыкального магаизна. Где по столбцу \"idOrder\" будет заказ, а по столбцу \"idAudio_Track\" будет песня. Использована связь \"многие к многим\", так как у одного заказа может быть много песен и у одной песни может быть множество заказов.';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
