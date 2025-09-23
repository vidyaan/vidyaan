show databases
create database gaming_club
use gaming_club
create table members(
id int auto_increment not null primary key,
name varchar(50),
bio text,
age int,
gender enum("Male", "Female", "Others"),
email_id varchar(100),
password varchar(20),
role enum("Player", "Admin"),
status enum("Active", "Inactive"),
balance float,
created_at timestamp default current_timestamp,
updated_at timestamp default null on update current_timestamp
);
select * from members;
insert into members (name, bio, age, gender, email_id, password, role, status, balance) values
("Akshay Rao", "I love Gaming!", 24, "Male", "akshay@test.com", "Akshay@123", "Player", "Active", 0),
("Ajay Rao", "I like to Play Games!", 23, "Male", "ajay@test.com", "Ajay@123", "Player", "Active", 0),
("Pavan", "I like BGMI!", 25, "Male", "pavan@test.com", "Pavan@123", "Player", "Inactive", 0),
("Murthy", "I'm Pro Player!", 23, "Male", "murthy@test.com", "Murthy@123", "Player", "Active", 0);
select * from members;
create table games (
id int not null auto_increment primary key,
name varchar(50),
description text,
cost_per_minute float,
category enum("Battle Royale", "Arcade", "Puzzle", "Shooting")
);
select * from games;
insert into games (name, description, cost_per_minute, category) values
("BGMI", "Multi-player Battle Royale Game", 5.32, "Battle Royale"),
("COC", "Multi-Player Statergy Based Game", 4.52, "Arcade"),
("COD", "Multi-player Battle Royale Game", 5.22, "Battle Royale"),
("Candy Crush", "Single Player Game to Crush the Candies", 3.28, "Puzzle");
select * from games;
create table recharge(
id int not null auto_increment primary key,
member_id int,
amount float,
recharge_date timestamp default current_timestamp
);
 Alter table recharge
Add Foreign Key(member_id) References members(id);
 Select * from recharge;
insert into recharge (member_id, amount) values
(1, 500.25),
(2, 255.65),
(3, 327.76),
(4, 535.87);
Select * from recharge;
create table game_session(
id int not null auto_increment primary key,
member_id int,
game_id int,
start_time timestamp default current_timestamp,
end_time timestamp default null on update current_timestamp,
total_amount float
);
select * from game_session;
alter table game_session
add Foreign Key(member_id) References members(id),
add Foreign Key(game_id) References games(id);
insert into game_session (member_id, game_id) values
(1, 2),
(2, 3),
(3, 1),
(4, 2);
select * from game_session;
truncate table recharge;
DELIMITER //
CREATE TRIGGER update_balance_after_recharge
AFTER INSERT ON recharge
FOR EACH ROW
BEGIN
    UPDATE members 
    SET balance = balance + NEW.amount,
        updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.member_id;
END;//
DELIMITER ;
insert into recharge (member_id, amount) values
(1, 500.25),
(2, 255.65),
(3, 327.76),
(4, 535.87);
Select * from members;
insert into recharge (member_id, amount) values
(1, 100.65),
(2, 85.25);
Select * from members;
delete from game_session where id > 0;
insert into game_session (member_id, game_id) values
(1, 3);
select * from game_session;
truncate table game_session;
DELIMITER //
CREATE TRIGGER calculate_total_amount
BEFORE UPDATE ON game_session
FOR EACH ROW
BEGIN
    IF NEW.end_time IS NOT NULL AND (OLD.end_time IS NULL OR NEW.end_time != OLD.end_time) THEN
        SET @minutes_played = TIMESTAMPDIFF(MINUTE, OLD.start_time, NEW.end_time);        
        IF @minutes_played < 1 THEN
            SET @minutes_played = 1;
        END IF;
        SELECT cost_per_minute INTO @cost_per_minute FROM games WHERE id = OLD.game_id;
        SET NEW.total_amount = @minutes_played * @cost_per_minute;
    END IF;
END;//
DELIMITER ;
insert into game_session (member_id, game_id) values
(1, 3),
(2,1);
update game_session set end_time = current_timestamp where member_id = 1;
update game_session set end_time = current_timestamp where member_id = 2;
