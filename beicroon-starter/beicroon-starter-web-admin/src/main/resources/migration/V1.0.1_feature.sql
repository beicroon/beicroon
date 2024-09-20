INSERT INTO `admin_account_admin`
    (`id`, `code`, `nickname`, `username`, `password`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808302, 'admin', 'admin', 'admin', 'faf1f45a58caa5b6bdb451adb8a71dfe697472e5c85516ec40fde6f989719610', 'admin', 'admin');

INSERT INTO `admin_resource_menu`
    (`id`, `parent_id`, `parent_code`, `parent_name`, `code`, `name`, `path`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808002, 0, '', '', 'ACCOUNT_CENTER', '账号中心', '/account/center', 'admin', 'admin'),
    (1825707094248591361, 1825706731063808002, 'ACCOUNT_CENTER', '账号中心', 'ACCOUNT_MANAGER', '账号管理', '/account/manager', 'admin', 'admin'),
    (1825707293197012994, 1825707094248591361, 'ACCOUNT_MANAGER', '账号管理', 'ACCOUNT_ADMIN', '后台账号', '/account/admin', 'admin', 'admin'),
    (1825707485132558337, 0, '', '', 'SETTING_CENTER', '设置中心', '/setting/center', 'admin', 'admin'),
    (1825707757028315138, 1825707485132558337, 'SETTING_CENTER', '设置中心', 'SETTING_SYSTEM', '系统设置', '/setting/system', 'admin', 'admin'),
    (1825707969033605121, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_MENU', '菜单管理', '/resource/menu', 'admin', 'admin'),
    (1825707969033606521, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_ROLE', '角色管理', '/resource/role', 'admin', 'admin');

INSERT INTO `admin_resource_role`
    (`id`, `code`, `name`, `remark`, `root_flag`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808102, 'ROOT', '超级管理员', '超级管理员', 'true', 'admin', 'admin'),
    (1825706731063809202, 'ADMIN', '管理员', '管理员', 'false', 'admin', 'admin');

insert into `admin_resource_admin_role_relation`
    (`admin_id`, `role_id`, `creator_name`, `modifier_name`)
values
    (1825706731063808302, 1825706731063808102, 'admin', 'admin');