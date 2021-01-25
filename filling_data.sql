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
 VALUES (1,'Justified','A Day To Remember',1,1,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview123/v4/7f/15/55/7f155579-1546-6db2-4ad7-7ad1c58863a3/mzaf_17300041138141320847.plus.aac.ep.m4a','https://avatars.yandex.net/get-music-content/33216/3a7989e4.a.3743602-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (2,'Bad Vibrations','A Day To Remember',1,1,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview113/v4/cd/c6/fe/cdc6fec5-f70e-66ee-f9ce-b748d52c6b99/mzaf_16203600042085887795.plus.aac.ep.m4a','https://avatars.yandex.net/get-music-content/33216/3a7989e4.a.3743602-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (3,'Parking Lot','blink-182',2,2,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview111/v4/9e/dc/f3/9edcf39d-1685-0402-3818-88b72d12c0ce/mzaf_3003446142790264855.plus.aac.ep.m4a','https://avatars.yandex.net/get-music-content/95061/49167688.a.4345731-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (4,'Rabbit Hole','blink-182',2,2,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview111/v4/cb/7a/85/cb7a85ea-ca39-1bf4-dfe6-de97c4cbe99c/mzaf_2485288544278214000.plus.aac.ep.m4a','https://avatars.yandex.net/get-music-content/95061/49167688.a.4345731-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (5,'Common Ground','Our Last Night',3,1,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview127/v4/45/d5/58/45d55843-d353-09a2-a893-c21a3e96fc63/mzaf_8632159435059538934.plus.aac.ep.m4a','http://e-cdn-images.deezer.com/images/cover/2349811734fcb0c9b7192888ac1ea1eb/200x200-000000-80-0-0.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (6,'Hardwired','Metallica',4,3,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview118/v4/b9/44/6b/b9446b3d-b04a-93b7-4c81-2c78fb854926/mzaf_5450424499069822558.plus.aac.ep.m4a','https://avatars.yandex.net/get-music-content/143117/113db587.a.3935282-1/200x200');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (7,'Tongue Tied','Our Last Night',3,1,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview117/v4/fb/04/8d/fb048d7d-45fa-240f-ae3c-7f335d49e5a1/mzaf_4533914054114645659.plus.aac.ep.m4a','http://cdn-images.deezer.com/images/cover/e3bbb7f067f0c8984fdc3ae3beae5876/200x200-000000-80-0-0.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (8,'Come As You Are','Nirvana',5,4,1,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview128/v4/7b/f7/c4/7bf7c46b-3655-2997-a2af-d55b40e6eb6d/mzaf_6346794254850943264.plus.aac.ep.m4a','http://worldelectricguitar.ru/img.lessons/Come_As_You_Are/1.jpg');
INSERT INTO `audio_track` (`idAudio_Track`,`name`,`artist`,`idAlbum`,`idGenre`,`price`,`link`,`image_link`)
 VALUES (9,'Smell Like Teen Spirit','Nirvana',5,4,2,'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview114/v4/68/2e/dc/682edcc0-0d83-72d1-eab9-c2b29ed0af46/mzaf_2379347647540479044.plus.aac.ep.m4a','https://upload.wikimedia.org/wikipedia/ru/thumb/3/3c/Smells_Like_Teen_Spirit.jpg/200px-Smells_Like_Teen_Spirit.jpg');


COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `user` (`login`,`password`,`email`,`role`,`bonus`)
 VALUES ('admin','21232f297a57a5a743894a0e4a801fc3','ilia.scherbakov42@gmail.com','admin',NULL);

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