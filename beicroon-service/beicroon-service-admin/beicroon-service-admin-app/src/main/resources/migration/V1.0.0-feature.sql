drop table if exists `admin_resource`;
create table `admin_resource`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `parent_id`     bigint(20) unsigned not null default 0 comment '父级主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `name`          varchar(255)        not null default '' comment '名称',
    `path`          varchar(255)        not null default '' comment '路由',
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
    index `idx_parent_id` (`parent_id`)
) engine = innodb comment '资源';

drop table if exists `admin_role`;
create table `admin_role`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `name`          varchar(255)        not null default '' comment '名称',
    `system_flag`   varchar(32)         not null default '' comment '是否系统创建',
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
    primary key (`id`)
) engine = innodb comment '角色';

drop table if exists `admin_account`;
create table `admin_account`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `code`          varchar(64)         not null default '' comment '编码',
    `name`          varchar(255)        not null default '' comment '名称',
    `username`      varchar(255)        not null default '' comment '登录账号',
    `password`      varchar(255)        not null default '' comment '登录密码',
    `phone`         varchar(255)        not null default '' comment '联系电话',
    `email`         varchar(255)        not null default '' comment '邮箱地址',
    `system_flag`   varchar(32)         not null default '' comment '是否系统创建',
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
    primary key (`id`)
) engine = innodb comment '账号';

drop table if exists `admin_account_role_relation`;
create table `admin_account_role_relation`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `account_id`    bigint(20) unsigned not null default 0 comment '账号主键',
    `role_id`       bigint(20) unsigned not null default 0 comment '角色主键',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(128)        not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(128)        not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(128)        not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_account_id` (`account_id`),
    index `idx_role_id` (`role_id`)
) engine = innodb comment '账号角色关系';

drop table if exists `admin_role_resource_relation`;
create table `admin_role_resource_relation`
(
    `id`            bigint(20) unsigned not null auto_increment comment '主键',
    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
    `role_id`       bigint(20) unsigned not null default 0 comment '角色主键',
    `resource_id`   bigint(20) unsigned not null default 0 comment '资源主键',
    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
    `creator_code`  varchar(128)        not null default '' comment '创建人编码',
    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
    `modifier_code` varchar(128)        not null default '' comment '更新人编码',
    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
    `removed_at`    timestamp           null     default null comment '删除时间',
    `remover_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
    `remover_code`  varchar(128)        not null default '' comment '删除人编码',
    `remover_name`  varchar(255)        not null default '' comment '删除人昵称',
    primary key (`id`),
    index `idx_role_id` (`role_id`),
    index `idx_resource_id` (`resource_id`)
) engine = innodb comment '角色资源关系';
