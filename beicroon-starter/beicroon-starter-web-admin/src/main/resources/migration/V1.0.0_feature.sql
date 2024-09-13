drop table if exists `admin_account_admin`;
create table `admin_account_admin`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `username`      varchar(255)        not null default '' comment '账号',
    `password`      varchar(255)        not null default '' comment '密码',
    `nickname`      varchar(255)        not null default '' comment '昵称',
    `phone`         varchar(32)         not null default '' comment '电话',
    `email`         varchar(255)        not null default '' comment '邮箱',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `deleted_at`    timestamp           null     default null comment '删除时间',
    `deleter_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `deleter_code`  varchar(64)         not null default '' comment '删除人编码',
    `deleter_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_username` (`username`),
    index `idx_phone` (`phone`),
    index `idx_email` (`email`)
) engine = innodb comment '后台账号';

INSERT INTO `admin_account_admin`
    (`code`, `nickname`, `username`, `password`, `creator_name`, `modifier_name`)
VALUES
    ('admin', 'admin', 'admin', 'faf1f45a58caa5b6bdb451adb8a71dfe697472e5c85516ec40fde6f989719610', 'admin', 'admin');

drop table if exists `admin_resource_menu`;
create table `admin_resource_menu`
(
    `id`            bigint(20) unsigned  not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned  not null default 0 comment '租户主键',
    `parent_id`     bigint(20) unsigned  not null default 0 comment '父级主键',
    `parent_code`   varchar(64)          not null default '' comment '父级编码',
    `parent_name`   varchar(255)         not null default '' comment '父级名称',
    `code`          varchar(64)          not null default '' comment '编码',
    `name`          varchar(255)         not null default '' comment '名称',
    `path`          varchar(255)         not null default '' comment '路径',
    `sorting`       smallint(5) unsigned not null default 0 comment '排序',
    `disabled_at`   timestamp            null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned  not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)          not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)          not null default '' comment '禁用人名称',
    `created_at`    timestamp            not null default current_timestamp comment '创建时间',
    `modified_at`   timestamp            not null default current_timestamp on update current_timestamp comment '更新时间',
    `creator_id`    bigint(20) unsigned  not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)          not null default '' comment '创建人编码',
    `creator_name`  varchar(255)         not null default '' comment '创建人昵称',
    `modifier_id`   bigint(20) unsigned  not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)          not null default '' comment '更新人编码',
    `modifier_name` varchar(255)         not null default '' comment '更新人昵称',
    `deleted_at`    timestamp            null     default null comment '删除时间',
    `deleter_id`    bigint(20) unsigned  not null default 0 comment '删除人主键',
    `deleter_code`  varchar(64)          not null default '' comment '删除人编码',
    `deleter_name`  varchar(255)         not null default '' comment '删除人昵称',
    primary key (`id`)
) engine = innodb comment '资源菜单';

INSERT INTO `admin_resource_menu`
    (`id`, `tenant_id`, `parent_id`, `parent_code`, `parent_name`, `code`, `name`, `path`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808002, 0, 0, '', '', 'ACCOUNT_CENTER', '账号中心', '/account/center', 'admin', 'admin'),
    (1825707094248591361, 0, 1825706731063808002, 'ACCOUNT_CENTER', '账号中心', 'ACCOUNT_MANAGER', '账号管理', '/account/manager', 'admin', 'admin'),
    (1825707293197012994, 0, 1825707094248591361, 'ACCOUNT_MANAGER', '账号管理', 'ACCOUNT_ADMIN', '后台账号', '/account/admin', 'admin', 'admin'),
    (1825707485132558337, 0, 0, '', '', 'SETTING_CENTER', '设置中心', '/setting/center', 'admin', 'admin'),
    (1825707757028315138, 0, 1825707485132558337, 'SETTING_CENTER', '设置中心', 'SETTING_SYSTEM', '系统设置','/setting/system', 'admin', 'admin'),
    (1825707969033605121, 0, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_MENU', '菜单管理', '/resource/menu', 'admin', 'admin'),
    (1825707969033606521, 0, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_ROLE', '角色管理', '/resource/role', 'admin', 'admin');


