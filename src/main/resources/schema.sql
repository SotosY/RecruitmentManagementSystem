CREATE TABLE IF NOT EXISTS `user` (
    `user_id` bigint(20) AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `is_active` bit(1),
    `password` varchar(255) DEFAULT NULL,
    `roles` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
    );