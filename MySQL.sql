/* Our Database */
CREATE DATABASE avc;
USE avc;

/* Users */
CREATE TABLE IF NOT EXISTS `users` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `name`        VARCHAR(100)     NOT NULL DEFAULT '',
  `money`       DOUBLE           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/* Jobs */
CREATE TABLE IF NOT EXISTS `lemonade` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `bank` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `car` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `donut` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `hockey` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `movie` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `news` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `oil` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `pizza` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `shrimp` (
  `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid`        VARCHAR(100)     NOT NULL DEFAULT '',
  `level`       INT(3)           NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`uuid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;