create or replace database cda_final_marc;
use cda_final_marc;


-- Création de la structure de la base de données
create or replace table game (id int primary key auto_increment, title varchar (50) not null, min_players int not null, max_players int not null );
create or replace table player (id int primary key auto_increment, email varchar (50) not null, nickname varchar (50) not null );
create or replace table contest (id int primary key auto_increment, game_id int not null, start_date date, winner_id int);
create or replace table player_contest (id int primary key auto_increment, player_id int, contest_id int);

-- Création des contraintes
alter table contest add constraint fk_contest_game foreign key (game_id) references game(id);
alter table contest add constraint fk_contest_player foreign key (winner_id) references player(id);
alter table player_contest add constraint fk_player_contest_player foreign key (player_id) references player(id);
alter table player_contest add constraint fk_player_contest_contest foreign key (contest_id) references contest(id);

-- hydratation de la base de données
insert into game (title,min_players,max_players) values 
("7Wonders", 2, 7),
("Ticket to Ride", 2, 5),
("Pandemic", 2,4),
("Munchkin", 3, 6);

insert into player (email, nickname) values
("luke.skywalker@rogue.sw","Luke"),
("amidala.padme@naboo.gov","Padme"),
("han.solo@millenium-falcon.com","HanSolo"),
("chewbacca@wook.ie","Chewbie"),
("rey@jakku.planet","Rey");

insert into contest (game_id, start_date,winner_id) values
(1,"2019-12-25",2),
(2,"2020-12-25",null);

insert into player_contest (player_id, contest_id) values
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(2,2),
(3,2),
(5,2);

