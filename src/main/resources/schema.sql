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

CREATE TABLE IF NOT EXISTS `job` (
    `job_id` bigint(20) AUTO_INCREMENT,
    `employer_id` bigint(20) DEFAULT NULL,
    `status` varchar(255) DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    `department` varchar(255) DEFAULT NULL,
    `company` varchar(255) DEFAULT NULL,
    `managed_by` varchar(255) DEFAULT NULL,
    `location` varchar(255) DEFAULT NULL,
    `salary` varchar(255) DEFAULT NULL,
    `post_date` varchar(255) DEFAULT NULL,
    `active_date` varchar(255) DEFAULT NULL,
    `expiry_date` varchar(255) DEFAULT NULL,
    `starting_date` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `requirements` varchar(255) DEFAULT NULL,
    `essential_criteria` varchar(255) DEFAULT NULL,
    `desirable_criteria` varchar(255) DEFAULT NULL,
    `salary_and_benefits` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`job_id`),
    CONSTRAINT `fk_employer_id_to_job` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`employer_id`)
    );

CREATE TABLE IF NOT EXISTS `application` (
    `application_id` bigint(20) AUTO_INCREMENT,
    `applicant_id` bigint(20) DEFAULT NULL,
    `job_id` bigint(20) DEFAULT NULL,
    `apply_date` varchar(255) DEFAULT NULL,
    `application_status` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`application_id`),
    CONSTRAINT `fk_applicant_id_to_application` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`applicant_id`),
    CONSTRAINT `fk_job_id_to_application` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`)
    );