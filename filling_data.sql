-- -----------------------------------------------------
-- Data for table `audio_portal`.`Album`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`album` (`idAlbum`, `album_name`, `studio`, `date`)
 VALUES ('1', 'Bad Vibrations', 'ADTR Records', '2016.02.09');
INSERT INTO `audio_portal`.`album` (`idAlbum`, `album_name`, `studio`, `date`)
 VALUES ('2', 'California', 'Foxy Studios', '2017.05.19');
INSERT INTO `audio_portal`.`album` (`idAlbum`, `album_name`, `studio`, `date`)
 VALUES ('3', 'Selective Hearing', 'OLN Records', '2017.06.09');
INSERT INTO `audio_portal`.`album` (`idAlbum`, `album_name`, `studio`, `date`)
 VALUES ('4', 'Hardwired... To Self-Destruct', 'HQ', '2016.10.18');
INSERT INTO `audio_portal`.`album` (`idAlbum`, `album_name`, `studio`, `date`)
 VALUES ('5', 'Nevermind', 'Sound City Studios', '1991.09.24');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`genre` (`idGenre`, `genre`)
 VALUES ('1', 'Post - Hardcore');
INSERT INTO `audio_portal`.`genre` (`idGenre`, `genre`)
 VALUES ('2', 'Pop - Punk');
INSERT INTO `audio_portal`.`genre` (`idGenre`, `genre`)
 VALUES ('3', 'Metal');
INSERT INTO `audio_portal`.`genre` (`idGenre`, `genre`)
 VALUES ('4', 'Grunge');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Audio_Track`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('1', 'Justified', 'A Day To Remember', '1', '1', '2.00', 'ADTR - Justified.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('2', 'Bad Vibrations', 'A Day To Remember', '1', '1', '2.00', 'ADTR - Bad Vibrations.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('3', 'Parking Lot', 'blink-182', '2', '2', '2.00', 'blink-182 - Parking Lot.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('4', 'Rabbit Hole', 'blnk-182', '2', '2', '2.00', 'blink-182 - Rabbit Hole.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('5', 'Common Ground', 'Our Last Night', '3', '1', '1.50', 'OLN - Common Ground.lossless');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('6', 'Hardwired', 'Metallica', '4', '3', '2.25', 'Metallica - Hardwired.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('7', 'Tongue Tied', 'Our Last Night', '3', '1', '1.50', 'OLN - Tongue Tied.lossless');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('8', 'Come As You Are', 'Nirvana', '5', '4', '1.12', 'Nirvana - Come As U R.mp3');
INSERT INTO `audio_portal`.`audio_track` (`idAudio_Track`, `name`, `artist`, `idAlbum`, `idGenre`, `price`, `link`)
 VALUES ('9', 'Smell Like Teen Spirit', 'Nirvana', '5', '4', '1.50', 'Nirvana - SLTS.mp3');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`user` (`login`, `password`, `email`, `role`, `bonus`)
 VALUES ('ilya', 'U2FsdGVkX1/ejnKH28IEun7u2kj5IeZL1qduRUesOoo=', 'ilyascher98@gmail.com', 'client', 'NULL');
INSERT INTO `audio_portal`.`user` (`login`, `password`, `email`, `role`, `bonus`)
 VALUES ('main_admin', 'U2FsdGVkX18ORK86cYGuqF1f0uOGjWh+3OU4wryYC2m9ijrNMfL2G9zEyFxd2I8N', 'adm80@gmail.com', 'admin', 'NULL');
INSERT INTO `audio_portal`.`user` (`login`, `password`, `email`, `role`, `bonus`)
 VALUES ('pewdiepie', 'U2FsdGVkX19zF+Pv2VJ5qTr9NPTufhzDJBYly455FCs=', 'pewpew@mail.com', 'client', '15%');
INSERT INTO `audio_portal`.`user` (`login`, `password`, `email`, `role`, `bonus`)
 VALUES ('GospodinPanda', 'U2FsdGVkX19doDrMomDiObU6ajnCKkbG8CEkEcj1a/g=', 'panda97@mail.com', 'client', '2%');
INSERT INTO `audio_portal`.`user` (`login`, `password`, `email`, `role`, `bonus`)
 VALUES ('ermakZero', 'U2FsdGVkX1/oKIwBi1OXO7clE0V/pSaAomjoPQvzBSs=', 'erzer87@mail.com', 'client', 'NULL');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`comments` (`idComments`, `idAudio_Track`, `login`, `text`, `date`)
 VALUES ('1', '2', 'ilya', 'Sounds cool', '17.12.06');
INSERT INTO `audio_portal`.`comments` (`idComments`, `idAudio_Track`, `login`, `text`, `date`)
 VALUES ('2', '3', 'pewdiepie', 'blink-182 again in business', '17.12.06');
INSERT INTO `audio_portal`.`comments` (`idComments`, `idAudio_Track`, `login`, `text`, `date`)
 VALUES ('3', '4', 'ilya', 'That guys always amaze', '17.10.05');
INSERT INTO `audio_portal`.`comments` (`idComments`, `idAudio_Track`, `login`, `text`, `date`)
 VALUES ('4', '5', 'GospodinPanda', 'Очень интересный подход', '17.10.02');
INSERT INTO `audio_portal`.`comments` (`idComments`, `idAudio_Track`, `login`, `text`, `date`)
 VALUES ('5', '9', 'ermakZero', 'Старая добрая нирвана', '17.10.02');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Order`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`order` (`idOrder`, `login`, `card_number`, `svc_code`, `date`)
 VALUES ('1', 'ilya', 'U2FsdGVkX1/v89NP6IBrLyWXtGsGZzIXU/51y+EWHqU=', 'U2FsdGVkX1//dQklPjefCNyfzbCAfKwdzTL9lKNfgo0=', '17.12.02');
INSERT INTO `audio_portal`.`order` (`idOrder`, `login`, `card_number`, `svc_code`, `date`)
 VALUES ('2', 'pewdiepie', 'U2FsdGVkX1+9nGm5pFB9i7gSxXGTqV6nAlFCcjfmykc=', 'U2FsdGVkX19tOctDIU1LPYEy6HdImFdpbTudPXiIhYQ=', '17.12.01');
INSERT INTO `audio_portal`.`order` (`idOrder`, `login`, `card_number`, `svc_code`, `date`)
 VALUES ('3', 'GospodinPanda', 'U2FsdGVkX1/GoKhSpfXA/VVsv8ouBi6MT56LcIA5T74=', 'U2FsdGVkX1/PSwxfw7/O3pthjdJSgQRwolNupySgWEE=', '17.12.03');
INSERT INTO `audio_portal`.`order` (`idOrder`, `login`, `card_number`, `svc_code`, `date`)
 VALUES ('4', 'ermakZero', 'U2FsdGVkX1/pz7B/tbfMxbcDr84i2Sa+DT2rfDa1GYo=', 'U2FsdGVkX1/pgNZ9jbrTQUHoHYed/u4Yn+iP2ePupFU=', '16.02.04');

COMMIT;
 
-- -----------------------------------------------------
-- Data for table `audio_portal`.`Order_List`
-- -----------------------------------------------------
START TRANSACTION;
USE `audio_portal`;
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('1', '1');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('1', '2');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('1', '3');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('2', '8');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('3', '4');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('3', '5');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('4', '7');
INSERT INTO `audio_portal`.`order_list` (`idOrder`, `idAudio_Track`)
 VALUES ('4', '8');

COMMIT;