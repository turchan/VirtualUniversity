CREATE TABLE `Users` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`login` varchar(50) NOT NULL,
	`password` varchar(20) NOT NULL,
	`name` varchar(50) NOT NULL,
	`surname` varchar(50) NOT NULL,
	`country` varchar(50),
	`email` varchar(100) NOT NULL,
	`city` varchar(50),
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `Courses` (
	`course_id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(100) NOT NULL,
	`password` varchar(20) NOT NULL,
	`professor` varchar(100) NOT NULL,
	`description` TEXT,
	`creator` varchar(100) NOT NULL,
	PRIMARY KEY (`course_id`)
);

CREATE TABLE `Users_Roles` (
	`user_id` INT NOT NULL,
	`role_id` INT NOT NULL,
	PRIMARY KEY (`user_id`,`role_id`)
);

CREATE TABLE `Users_Courses` (
	`user_id` INT NOT NULL,
	`course_id` INT NOT NULL,
	PRIMARY KEY (`user_id`,`course_id`)
);

CREATE TABLE `Marks` (
	`mark_id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(100) NOT NULL,
	`mark` INT NOT NULL,
	`course_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	PRIMARY KEY (`mark_id`)
);

CREATE TABLE `Materials` (
	`material_id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(100) NOT NULL,
	`description` TEXT,
	`course_id` INT NOT NULL,
	`file` INT NOT NULL,
	PRIMARY KEY (`material_id`)
);

CREATE TABLE `Roles` (
	`role_id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(20) NOT NULL,
	PRIMARY KEY (`role_id`)
);

CREATE TABLE `Files` (
	`file_id` INT NOT NULL AUTO_INCREMENT,
	`filename` varchar(100) NOT NULL,
	`file` varchar(100) NOT NULL,
	`material_id` INT NOT NULL,
	PRIMARY KEY (`file_id`)
);

ALTER TABLE `Users_Roles` ADD CONSTRAINT `Users_Roles_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`);

ALTER TABLE `Users_Roles` ADD CONSTRAINT `Users_Roles_fk1` FOREIGN KEY (`role_id`) REFERENCES `Roles`(`role_id`);

ALTER TABLE `Users_Courses` ADD CONSTRAINT `Users_Courses_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`);

ALTER TABLE `Users_Courses` ADD CONSTRAINT `Users_Courses_fk1` FOREIGN KEY (`course_id`) REFERENCES `Courses`(`course_id`);

ALTER TABLE `Marks` ADD CONSTRAINT `Marks_fk0` FOREIGN KEY (`course_id`) REFERENCES `Courses`(`course_id`);

ALTER TABLE `Marks` ADD CONSTRAINT `Marks_fk1` FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`);

ALTER TABLE `Materials` ADD CONSTRAINT `Materials_fk0` FOREIGN KEY (`course_id`) REFERENCES `Courses`(`course_id`);

ALTER TABLE `Files` ADD CONSTRAINT `Files_fk0` FOREIGN KEY (`material_id`) REFERENCES `Materials`(`material_id`);

