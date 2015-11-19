set names 'utf8';
create database fasterinput default character set 'utf8';
grant all privileges on fasterinput.* to 'fasterinput'@'localhost' identified by 'vozmyotFac9' with grant option;

use fasterinput;

create table if not exists `tb_share` (
  `id` bigint(20) not null auto_increment comment '自增id',
  `md5` varchar(32) not null default '' comment '内容md5校验值',
  `shareTime` datetime not null default '1970-01-01 00:00:00' comment '分享时间',
  `content` text COLLATE utf8mb4_unicode_ci not null default '' comment '分享内容',
  primary key (`id`),
  unique key `unq_share_md5` (`md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table if not exists `tb_account` (
  `id` bigint(20) not null auto_increment comment '自增id',
  `accountFrom` tinyint(1) not null comment '第三方帐号来源',
  `openId` varchar(48) not null comment '第三方账号id',
  `enable` tinyint(1) not null comment '帐号是否可用',
  `regDate` datetime not null comment '帐号注册日期',
  `lastLogin` datetime not null comment '最后登录日期',
  `nickName` varchar(32) not null comment '帐号昵称',
  `gender` tinyint(1) not null comment '帐号性别',
  `avatarUrl` varchar(256) not null comment '帐号avatar url',
  primary key (`id`),
  unique key `third_account` (`accountFrom`, `openId`),
  index `idx_nickName` (`nickName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;