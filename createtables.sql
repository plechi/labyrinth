CREATE DATABASE neptun;

USE neptun;

GRANT SELECT, INSERT, UPDATE, DELETE ON neptun.* TO neptun IDENTIFIED BY 'XXXXX';

CREATE TABLE IF NOT EXISTS `users` (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(40),
	password CHAR(32),
	full_name VARCHAR(100),
	email VARCHAR(100),
	type INT DEFAULT 0);
 
CREATE TABLE IF NOT EXISTS usage_log (
	id INT AUTO_INCREMENT PRIMARY KEY,
	time TIMESTAMP,
	entry TEXT,
	user_id INT,
	level INT,
	INDEX (time),
	INDEX (level));
