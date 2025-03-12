drop table if exists `home_account`;
create table `home_account`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `wx_unionid`    varchar(255)        not null default '' comment '微信unionid',
    `wx_openid_xcx` varchar(255)        not null default '' comment '微信小程序openid',
    `wx_openid_ggh` varchar(255)        not null default '' comment '微信公众号openid',
    `code`          varchar(64)         not null default '' comment '编码',
    `name`          varchar(255)        not null default '' comment '名称',
    `username`      varchar(255)        not null default '' comment '登录账号',
    `password`      varchar(255)        not null default '' comment '登录密码',
    `phone`         varchar(255)        not null default '' comment '联系电话',
    `email`         varchar(255)        not null default '' comment '邮箱地址',
    `avatar`        varchar(1024)       not null default '' comment '头像链接',
    `role`          varchar(32)         not null default '' comment '角色',
    `balance`       decimal(10, 3)      not null default 0 comment '余额',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(128)        not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(128)        not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(128)        not null default '' comment '禁用人编码',
    `disabled_name` varchar(128)        not null default '' comment '禁用人名称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(128)        not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_wx_unionid` (`wx_unionid`),
    index `idx_wx_openid_xcx` (`wx_openid_xcx`),
    index `idx_wx_openid_ggh` (`wx_openid_ggh`),
    index `idx_username` (`username`),
    index `idx_phone` (`phone`)
) engine = innodb comment '用户';

drop table if exists `home_account_recharge_record_relation`;
create table `home_account_recharge_record_relation`
(
    `id`              bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`       bigint(20) unsigned not null default 0 comment '租户主键',
    `account_id`      bigint(20) unsigned not null default 0 comment '账号主键',
    `payment_id`      bigint(20) unsigned not null default 0 comment '支付主键',
    `recharge_amount` decimal(10, 3)      not null default 0 comment '充值金额',
    `bonus_amount`    decimal(10, 3)      not null default 0 comment '赠送金额',
    `origin_balance`  decimal(10, 3)      not null default 0 comment '充值前余额',
    `result_balance`  decimal(10, 3)      not null default 0 comment '充值后余额',
    `created_at`      timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`      bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`    varchar(128)        not null default '' comment '创建人编码',
    `creator_name`    varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`     timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`     bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code`   varchar(128)        not null default '' comment '更新人编码',
    `modifier_name`   varchar(255)        not null default '' comment '更新人昵称',
    primary key (`id`),
    index `idx_account_id` (`account_id`),
    index `idx_payment_id` (`payment_id`)
) engine = innodb comment '充值记录';

drop table if exists `home_account_expense_record_relation`;
create table `home_account_expense_record_relation`
(
    `id`             bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`      bigint(20) unsigned not null default 0 comment '租户主键',
    `account_id`     bigint(20) unsigned not null default 0 comment '账号主键',
    `order_id`       bigint(20) unsigned not null default 0 comment '订单主键',
    `expense_amount` decimal(10, 3)      not null default 0 comment '消费金额',
    `origin_balance` decimal(10, 3)      not null default 0 comment '消费前余额',
    `result_balance` decimal(10, 3)      not null default 0 comment '消费后余额',
    `created_at`     timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`     bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`   varchar(128)        not null default '' comment '创建人编码',
    `creator_name`   varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`    timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`    bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code`  varchar(128)        not null default '' comment '更新人编码',
    `modifier_name`  varchar(255)        not null default '' comment '更新人昵称',
    primary key (`id`),
    index `idx_account_id` (`account_id`),
    index `idx_order_id` (`order_id`)
) engine = innodb comment '消费记录';
