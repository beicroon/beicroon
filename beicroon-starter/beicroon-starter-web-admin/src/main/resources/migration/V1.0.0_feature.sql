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
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(64)         not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_username` (`username`),
    index `idx_phone` (`phone`),
    index `idx_email` (`email`)
) engine = innodb comment '后台账号';

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
    `path`          varchar(255)         not null default '' comment '路由',
    `sorting`       smallint(5) unsigned not null default 0 comment '排序',
    `created_at`    timestamp            not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned  not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)          not null default '' comment '创建人编码',
    `creator_name`  varchar(255)         not null default '' comment '创建人昵称',
    `modified_at`   timestamp            not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned  not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)          not null default '' comment '更新人编码',
    `modifier_name` varchar(255)         not null default '' comment '更新人昵称',
    `disabled_at`   timestamp            null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned  not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)          not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)          not null default '' comment '禁用人名称',
    `removed_at`    timestamp            null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned  not null default 0 comment '删除人主键',
    `remover_code`  varchar(64)          not null default '' comment '删除人编码',
    `remover_name`  varchar(255)         not null default '' comment '删除人昵称',
    primary key (`id`)
) engine = innodb comment '资源菜单';

drop table if exists `admin_resource_role`;
create table `admin_resource_role`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `name`          varchar(255)        not null default '' comment '名称',
    `remark`        varchar(4096)       not null default '' comment '备注',
    `root_flag`     varchar(32)         not null default 'false' comment '是否超级管理员',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(64)         not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`)
) engine = innodb comment '资源角色';

drop table if exists `admin_resource_role_menu_relation`;
create table `admin_resource_role_menu_relation`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `role_id`       bigint(20) unsigned not null default 0 comment '角色主键',
    `menu_id`       bigint(20) unsigned not null default 0 comment '菜单主键',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(64)         not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_role_id_menu_id` (`role_id`, `menu_id`)
) engine = innodb comment '角色菜单';

drop table if exists `admin_account_admin_role_relation`;
create table `admin_account_admin_role_relation`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `admin_id`      bigint(20) unsigned not null default 0 comment '后台账号主键',
    `role_id`       bigint(20) unsigned not null default 0 comment '角色主键',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `disabled_at`   timestamp           null     default null comment '禁用时间',
    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(64)         not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_admin_id_role_id` (`admin_id`, `role_id`)
) engine = innodb comment '后台账号角色';
