CREATE SCHEMA IF NOT EXISTS `spring_boot_learn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `spring_boot_learn`.`user` (
  `id` VARCHAR(37) NOT NULL COMMENT 'UUID',
  `username` VARCHAR(128) NOT NULL COMMENT '用户名',
  `age` INT(10) unsigned NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)  COMMENT ''
  )
ENGINE = InnoDB
COMMENT = '用户表';

CREATE SCHEMA IF NOT EXISTS `spring_boot_learn_2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `spring_boot_learn_2`.`account` (
  `id` VARCHAR(37) NOT NULL COMMENT 'UUID',
  `cellphone` VARCHAR(36) NOT NULL COMMENT '手机号',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)  COMMENT ''
  )
ENGINE = InnoDB
COMMENT = '账号表';