drop table if exists `admin_account`;
create table `admin_account`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `account`       varchar(255)        not null default '' comment '账号',
    `password`      varchar(255)        not null default '' comment '密码',
    `nickname`      varchar(255)        not null default '' comment '姓名',
    `phone`         varchar(32)         not null default '' comment '手机',
    `email`         varchar(255)        not null default '' comment '邮箱',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人姓名',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人姓名',
    primary key (`id`),
    index `idx_account` (`account`),
    index `idx_phone` (`phone`),
    index `idx_email` (`email`)
) engine = innodb comment '账号表';