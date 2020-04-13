CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `e_mail` varchar(150) UNIQUE NOT NULL,
  `phone` varchar(20) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `student` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int NOT NULL COMMENT 'Tells that a student is a user',
  `matricule` varchar(10) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `teacher` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int NOT NULL COMMENT 'Tells that a teacher is a user',
  `matricule` varchar(10) UNIQUE NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `term` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(10) NOT NULL COMMENT 'Term code. E.g S1',
  `name` varchar(100) NOT NULL COMMENT 'Term name. E.g Semestre 1',
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `class` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT 'exemple: Genie Logiciel 1ere annee',
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `subject` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(10) UNIQUE NOT NULL COMMENT 'course code. E.g CSC-394',
  `name` varchar(100) NOT NULL COMMENT 'exemple: Genie Logiciel 1ere annee',
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `course` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_term` int NOT NULL,
  `id_class` int NOT NULL,
  `id_subject` int NOT NULL,
  `id_teacher` int NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `student_course` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_course` int,
  `id_student` int,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `cat_survey` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100),
  `description` varchar(255),
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `question` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` text NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `cat_survey_question` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `seq_nbr` int NOT NULL COMMENT 'Used to sort the display of questions',
  `id_question` int NOT NULL,
  `id_cat_survey` int NOT NULL,
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `survey` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_cat_survey` int NOT NULL,
  `id_course` int NOT NULL,
  `title` varchar(45) UNIQUE NOT NULL,
  `description` text NOT NULL,
  `begin_message` text COMMENT 'Instruction avant de demarrer le survey',
  `end_message` text COMMENT 'Message de fin du survey',
  `begin_date` timestamp NOT NULL COMMENT 'begining of the survey',
  `end_date` timestamp NOT NULL COMMENT 'end of the survey',
  `status` tinyint NOT NULL COMMENT 'Status of the survey 0=new, 1 active, 2-completed, 3-cancelled',
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

CREATE TABLE `student_survey` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_survey` int,
  `id_student` int,
  `id_question` int,
  `comments` text COMMENT 'note if user has comments',
  `is_na` tinyint NOT NULL DEFAULT 0 COMMENT '0=student has responded. 1: either not applicable or prefer not to respond',
  `rating` int NOT NULL COMMENT 'Rating on a scale of 1 to 5',
  `create_date_time` timestamp NOT NULL COMMENT 'When created',
  `mod_date_time` timestamp NOT NULL COMMENT 'When changed',
  `mod_by` int NOT NULL COMMENT 'Who changed'
);

ALTER TABLE `users` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `student` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

ALTER TABLE `student` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `teacher` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

ALTER TABLE `teacher` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `term` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `class` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `subject` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `course` ADD FOREIGN KEY (`id_term`) REFERENCES `term` (`id`);

ALTER TABLE `course` ADD FOREIGN KEY (`id_class`) REFERENCES `class` (`id`);

ALTER TABLE `course` ADD FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`);

ALTER TABLE `course` ADD FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id`);

ALTER TABLE `course` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `student_course` ADD FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

ALTER TABLE `student_course` ADD FOREIGN KEY (`id_student`) REFERENCES `student` (`id`);

ALTER TABLE `student_course` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `cat_survey` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `question` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `cat_survey_question` ADD FOREIGN KEY (`id_question`) REFERENCES `question` (`id`);

ALTER TABLE `cat_survey_question` ADD FOREIGN KEY (`id_cat_survey`) REFERENCES `cat_survey` (`id`);

ALTER TABLE `cat_survey_question` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `survey` ADD FOREIGN KEY (`id_cat_survey`) REFERENCES `cat_survey` (`id`);

ALTER TABLE `survey` ADD FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

ALTER TABLE `survey` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);

ALTER TABLE `student_survey` ADD FOREIGN KEY (`id_survey`) REFERENCES `survey` (`id`);

ALTER TABLE `student_survey` ADD FOREIGN KEY (`id_student`) REFERENCES `student` (`id`);

ALTER TABLE `student_survey` ADD FOREIGN KEY (`id_question`) REFERENCES `question` (`id`);

ALTER TABLE `student_survey` ADD FOREIGN KEY (`mod_by`) REFERENCES `users` (`id`);
