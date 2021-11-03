CREATE TABLE IF NOT EXISTS `user` (
    `user_id` bigint(20) AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `is_active` bit(1),
    `password` varchar(255) DEFAULT NULL,
    `roles` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
    );

CREATE TABLE IF NOT EXISTS `applicant` (
    `applicant_id` bigint(20) AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    `city` varchar(255) DEFAULT NULL,
    `postcode` varchar(255) DEFAULT NULL,
    `date_of_birth` varchar(255) DEFAULT NULL,
    `gender` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`applicant_id`),
    CONSTRAINT `fk_user_id_to_applicant` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    );

CREATE TABLE IF NOT EXISTS `applicant_resume` (
    `resume_id` bigint(20) AUTO_INCREMENT,
    `applicant_id` bigint(20) DEFAULT NULL,
    `experience` varchar(255) DEFAULT NULL,
    `education` varchar(255) DEFAULT NULL,
    `cv` varchar(255) DEFAULT NULL,
    `cover_letter` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`resume_id`),
    CONSTRAINT `fk_applicant_id_to_applicant_resume` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`applicant_id`)
    );

CREATE TABLE IF NOT EXISTS `employer` (
    `employer_id` bigint(20) AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `contact_name` varchar(255) DEFAULT NULL,
    `company_email` varchar(255) DEFAULT NULL,
    `business_type` varchar(255) DEFAULT NULL,
    `telephone_number` varchar(255) DEFAULT NULL,
    `company_profile` varchar(255) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    `city` varchar(255) DEFAULT NULL,
    `postcode` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`employer_id`),
    CONSTRAINT `fk_user_id_to_employer` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    );