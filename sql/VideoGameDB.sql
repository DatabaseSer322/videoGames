BEGIN TRANSACTION;
CREATE TABLE `User_Account` (
	`Uid`	INTEGER NOT NULL,
	`FirstName`	TEXT NOT NULL,
	`LastName`	TEXT NOT NULL,
	`DateBirth`	TEXT NOT NULL,
	`Email`	TEXT NOT NULL,
	`Password`	TEXT NOT NULL,
	PRIMARY KEY(Uid)
);
INSERT INTO `User_Account` VALUES (1,'Crystal','Gutierrez','06/23/1993','gutierrezcrystal77@gmail.com','luis');
INSERT INTO `User_Account` VALUES (2,'Luis','Garcia','09/19/992','luis.garcia@gmail.com','lightning');
INSERT INTO `User_Account` VALUES (3,'Kristel','Licata','05/12/1991','Kristel.Licata@gmail.com','panCakes');
INSERT INTO `User_Account` VALUES (4,'Sergio','Salazar','11/05/1994','sersh123@gmail.com','gamer123');
INSERT INTO `User_Account` VALUES (5,'Dan','Lincon','01/07/1993','danlin@yahoo.com','pass1234');
INSERT INTO `User_Account` VALUES (6,'Yazmin','Caldera','12/23/2003','fawmin@hotmail.com','supergamer');
INSERT INTO `User_Account` VALUES (7,'Eric','Gutierrez','10/17/1999','eguti@gmail.com','elite');
CREATE TABLE `Games` (
	`Gid`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Title`	TEXT NOT NULL,
	`Rate_Star`	INTEGER NOT NULL,
	`Genre`	TEXT NOT NULL,
	`Rating_Age`	TEXT NOT NULL
);
INSERT INTO `Games` VALUES (1,'Portal',5,'Puzzle','E');
INSERT INTO `Games` VALUES (2,'Portal 2',5,'Puzzle','E');
INSERT INTO `Games` VALUES (3,'World of Warcraft',4,'MMO','M');
INSERT INTO `Games` VALUES (4,'Diablo 3',4,'MMO','M');
INSERT INTO `Games` VALUES (5,'Salnderman',3,'Horror','T');
INSERT INTO `Games` VALUES (6,'Mario Cars',5,'Race','E');
INSERT INTO `Games` VALUES (7,'Final Fantasy 13',4,'RPG','E+10');
INSERT INTO `Games` VALUES (8,'GTA5',4,'Adventure','M');
INSERT INTO `Games` VALUES (9,'Sonic 06',1,'Race','E');
INSERT INTO `Games` VALUES (10,'Destiny ',4,'Shooter','T');
INSERT INTO `Games` VALUES (11,'Minecraft',3,'Creation','E');
INSERT INTO `Games` VALUES (12,'Hello Kitty World',4,'MMO','EC');
INSERT INTO `Games` VALUES (13,'Dota 2',2,'MOBA','T');
INSERT INTO `Games` VALUES (14,'League Of Leagends ',3,'MOBA','T');
INSERT INTO `Games` VALUES (15,'Mortal Kombat',5,'Fightning','M');
INSERT INTO `Games` VALUES (16,'Skyrim',4,'RPG','M');
INSERT INTO `Games` VALUES (17,'The Sims',3,'Creation','T');
INSERT INTO `Games` VALUES (18,'Dead Space',4,'Horror','M');
INSERT INTO `Games` VALUES (19,'NBA 2K15',2,'Sport','E');
INSERT INTO `Games` VALUES (20,'Fifa 15',4,'Sport','E');
INSERT INTO `Games` VALUES (21,'Heros Of The Storm',4,'MOBA','T');
INSERT INTO `Games` VALUES (22,'Assassin''s Creed',4,'Adventure','M');
INSERT INTO `Games` VALUES (23,'Borderlands',3,'Shooter','M');
INSERT INTO `Games` VALUES (24,'Bloodborne',4,'Action','M');
INSERT INTO `Games` VALUES (25,'Pillars of Eternity',4,'RPG','E');
CREATE TABLE "Account_Games" (
	`Uid`	INTEGER NOT NULL,
	`Gid`	INTEGER NOT NULL,
	`Status`	TEXT NOT NULL,
	PRIMARY KEY(Uid,Gid)
);
INSERT INTO `Account_Games` VALUES (1,1,'Wish');
INSERT INTO `Account_Games` VALUES (1,2,'Current');
INSERT INTO `Account_Games` VALUES (1,5,'Current');
INSERT INTO `Account_Games` VALUES (2,10,'Wish');
INSERT INTO `Account_Games` VALUES (2,25,'Wish');
INSERT INTO `Account_Games` VALUES (4,6,'Current');
INSERT INTO `Account_Games` VALUES (4,13,'Current');
INSERT INTO `Account_Games` VALUES (7,20,'Current');
INSERT INTO `Account_Games` VALUES (7,25,'Wish');
INSERT INTO `Account_Games` VALUES (6,3,'Current');
INSERT INTO `Account_Games` VALUES (6,19,'Current');
INSERT INTO `Account_Games` VALUES (6,21,'Current');
INSERT INTO `Account_Games` VALUES (6,17,'Current');
INSERT INTO `Account_Games` VALUES (4,2,'Wish');
INSERT INTO `Account_Games` VALUES (5,18,'Current');
COMMIT;
