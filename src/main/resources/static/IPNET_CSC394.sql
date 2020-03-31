CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `e_mail` varchar(150) UNIQUE NOT NULL,
  `phone` varchar(20) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `student` (
  `id` int PRIMARY KEY,
  `matricule` varchar(10) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `teacher` (
  `id` int PRIMARY KEY,
  `matricule` varchar(10) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

ALTER TABLE `users` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `student` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `teacher` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);
