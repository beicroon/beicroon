package com.beicroon.starter.generic.content.migration;

public class MigrationContent {

    private static final String CONTENT = """
            #drop table if exists `example`;
            #create table `example`
            #(
            #    `id`            bigint(20) unsigned not null auto_increment comment '主键',
            #    `tenant_id`     bigint(20) unsigned not null default 0 comment '租户主键',
            #    `disabled_at`   timestamp           null     default null comment '禁用时间',
            #    `disabled_id`   bigint(20) unsigned not null default 0 comment '禁用人主键',
            #    `disabled_code` varchar(64)         not null default '' comment '禁用人编码',
            #    `disabled_name` varchar(64)         not null default '' comment '禁用人名称',
            #    `created_at`    timestamp           not null default current_timestamp comment '创建时间',
            #    `modified_at`   timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
            #    `creator_id`    bigint(20) unsigned not null default 0 comment '创建人主键',
            #    `creator_code`  varchar(64)         not null default '' comment '创建人编码',
            #    `creator_name`  varchar(255)        not null default '' comment '创建人昵称',
            #    `modifier_id`   bigint(20) unsigned not null default 0 comment '更新人主键',
            #    `modifier_code` varchar(64)         not null default '' comment '更新人编码',
            #    `modifier_name` varchar(255)        not null default '' comment '更新人昵称',
            #    `deleted_at`    timestamp               null default null comment '删除时间',
            #    `deleter_id`    bigint(20) unsigned not null default 0 comment '删除人主键',
            #    `deleter_code`  varchar(64)         not null default '' comment '删除人编码',
            #    `deleter_name`  varchar(255)        not null default '' comment '删除人昵称',
            #    primary key (`id`)
            #) engine = innodb comment '建表示例';
            
            CREATE DATABASE `dev-{{moduleName}}` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
            """;

    public static String getContent(String moduleName) {
        return CONTENT
                .replace("{{moduleName}}", moduleName);
    }

}