-- -----------------------------------------------------
-- Data for table `audio_portal`.`Album`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `album` (`idAlbum`,`album_name`,`studio`,`date`)
 VALUES (1,'Bad Vibrations','ADTR Records','2016-09-02');
INSERT INTO `album` (`idAlbum`,`album_name`,`studio`,`date`)
 VALUES (2,'California','Foxy Studios','2017-05-19');
INSERT INTO `album` (`idAlbum`,`album_name`,`studio`,`date`)
 VALUES (3,'Selective Hearing','OLN Records','2017-06-09');
INSERT INTO `album` (`idAlbum`,`album_name`,`studio`,`date`)
 VALUES (4,'Hardwired... To Self-Destruct','HQ','2016-10-18');
INSERT INTO `album` (`idAlbum`,`album_name`,`studio`,`date`)
 VALUES (5,'Nevermind','Sound City Studios','1991-09-24');

COMMIT;

-- -----------------------------------------------------
-- Data for table `audio_portal`.`Genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (1,'Post - Hardcore');
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (2,'Pop - Punk');
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (3,'Metal');
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (4,'Grunge');
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (6,'Hard Rock');
INSERT INTO `genre` (`idGenre`,`genre`)
 VALUES (7,'POP');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Audio_Track`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (1,'Justified','A Day To Remember',1,1,2,'https://drive.google.com/uc?export=download&id=1R71HKSIpZtDk6aGTVL2vEIm-ijhAPOku','https://avatars.yandex.net/get-music-content/33216/3a7989e4.a.3743602-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (2,'Bad Vibrations','A Day To Remember',1,1,2,'https://drive.google.com/uc?export=download&id=1TCsF9_JT_wL5HCc5RHm3OCwvr35NqI1I','https://avatars.yandex.net/get-music-content/33216/3a7989e4.a.3743602-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (3,'Parking Lot','blink-182',2,2,2,'https://drive.google.com/uc?export=download&id=1tCDYVE7bl3YpPWc2rCVtVblvtXbmALb7','https://avatars.yandex.net/get-music-content/95061/49167688.a.4345731-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (4,'Rabbit Hole','blink-182',2,2,2,'https://drive.google.com/uc?export=download&id=1ixTsm_0rAzPigkT7btCAR-fjTtVSU-v1','https://avatars.yandex.net/get-music-content/95061/49167688.a.4345731-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (5,'Common Ground','Our Last Night',3,1,2,'https://drive.google.com/uc?export=download&id=1gXq3E4HrGmEzXXjs03vuV06na-z3B5_o','http://e-cdn-images.deezer.com/images/cover/2349811734fcb0c9b7192888ac1ea1eb/200x200-000000-80-0-0.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (6,'Hardwired','Metallica',4,3,2,'https://drive.google.com/uc?export=download&id=1wedZX3LUmlceDU_Caa91Js1lFXiMDHYA','https://avatars.yandex.net/get-music-content/143117/113db587.a.3935282-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (7,'Tongue Tied','Our Last Night',3,1,2,'https://drive.google.com/uc?export=download&id=1VJLtK8BYjXerzQocq4xOSsA3xhg0v4jL','http://cdn-images.deezer.com/images/cover/e3bbb7f067f0c8984fdc3ae3beae5876/200x200-000000-80-0-0.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (8,'Come As You Are','Nirvana',5,4,1,'https://drive.google.com/uc?export=download&id=1s3rLATfYBR1MujlzJwRsoWioE6mBTfL1','http://worldelectricguitar.ru/img.lessons/Come_As_You_Are/1.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (9,'Smell Like Teen Spirit','Nirvana',5,4,2,'https://drive.google.com/uc?export=download&id=1s3rLATfYBR1MujlzJwRsoWioE6mBTfL1','https://upload.wikimedia.org/wikipedia/ru/thumb/3/3c/Smells_Like_Teen_Spirit.jpg/200px-Smells_Like_Teen_Spirit.jpg');


COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `user` (`login`,`password`,`email`,`role`,`bonus`)
 VALUES ('admin','20917c851c4a54f2a054390dac9085b7','ilyascher98@gmail.com','admin',NULL);

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `comment` (`idComments`,`idAudio_Track`,`login`,`text`,`date`)
 VALUES (1,2,'admin','Sounds cool','2006-12-17');
INSERT INTO `comment` (`idComments`,`idAudio_Track`,`login`,`text`,`date`)
 VALUES (3,4,'admin','That guys always amaze','2005-10-17');
INSERT INTO `comment` (`idComments`,`idAudio_Track`,`login`,`text`,`date`)
 VALUES (6,1,'admin','BAD VIBRATION!!!','2018-01-29');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Order`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `order` (`idOrder`,`login`,`card_number`,`svc_code`,`date`)
 VALUES (1,'admin','U2FsdGVkX1/v89NP6IBrLyWXtGsGZzIXU/51y+EWHqU=','U2FsdGVkX1//dQklPjefCNyfzbCAfKwdzTL9lKNfgo0=','2017-12-02');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Order_List`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `order_list` (`idOrder`,`idAudio_Track`)
 VALUES (1,1);
INSERT INTO `order_list` (`idOrder`,`idAudio_Track`)
 VALUES (1,2);
INSERT INTO `order_list` (`idOrder`,`idAudio_Track`)
 VALUES (1,3);

COMMIT;