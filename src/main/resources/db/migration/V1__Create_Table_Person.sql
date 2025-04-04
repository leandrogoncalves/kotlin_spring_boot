CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(6) NOT NULL ,
  `email` varchar(120) UNIQUE,
  `birth_day` datetime
)