drop database if exists MasterMindTestDB;

create database MasterMindTestDB;

use MasterMindTestDB;

create table Game(
	GameID int primary key auto_increment,
    GameStatus tinyint not null, 
    Answer char(4)
);

create table GameRound(
	RoundID int primary key auto_increment,
    GameID int not null,
    Guess char(4) not null,
    Exact char(1) not null,
    `Partial` char(1) not null,
    `TimeStamp` TIMESTAMP not null,
    foreign key (GameID) references Game(GameID)
);

