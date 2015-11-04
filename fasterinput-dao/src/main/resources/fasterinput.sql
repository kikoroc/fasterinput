set names 'utf8';
create database fasterinput default character set 'utf8';
grant all privileges on fasterinput.* to 'fasterinput'@'localhost' identified by 'vozmyotFac9' with grant option;

use fasterinput;

create table if not exists `tb_share` (
  `id` bigint(20) not null auto_increment comment '自增id',
  `md5` varchar(32) not null default '' comment '内容md5校验值',
  `shareTime` datetime not null default '1970-01-01 00:00:00' comment '分享时间',
  `content` text not null default '' comment '分享内容',
  primary key (`id`),
  unique key `unq_share_md5` (`md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;