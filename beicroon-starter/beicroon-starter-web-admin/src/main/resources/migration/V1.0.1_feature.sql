INSERT INTO `admin_account_admin`
    (`id`, `code`, `nickname`, `username`, `password`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808302, 'root', 'root', 'root', 'b13bc5fefc72a6a06c6c172d227f5c39967cdbbcdf8f20b5fc39f175bfb608a5', 'root', 'root');

INSERT INTO `admin_resource_menu`
    (`id`, `parent_id`, `parent_code`, `parent_name`, `code`, `name`, `path`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808002, 0, '', '', 'ACCOUNT_CENTER', '账号中心', '/account/center', 'root', 'root'),
    (1825707094248591361, 1825706731063808002, 'ACCOUNT_CENTER', '账号中心', 'ACCOUNT_MANAGER', '账号管理', '/account/manager', 'root', 'root'),
    (1825707293197012994, 1825707094248591361, 'ACCOUNT_MANAGER', '账号管理', 'ACCOUNT_ADMIN', '后台账号', '/account/admin', 'root', 'root'),
    (1825707485132558337, 0, '', '', 'SETTING_CENTER', '设置中心', '/setting/center', 'root', 'root'),
    (1825707757028315138, 1825707485132558337, 'SETTING_CENTER', '设置中心', 'SETTING_SYSTEM', '系统设置', '/setting/system', 'root', 'root'),
    (1825707969033605121, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_MENU', '菜单管理', '/resource/menu', 'root', 'root'),
    (1825707969033606521, 1825707757028315138, 'SETTING_SYSTEM', '系统设置', 'RESOURCE_ROLE', '角色管理', '/resource/role', 'root', 'root');

INSERT INTO `admin_resource_role`
    (`id`, `code`, `name`, `remark`, `root_flag`, `creator_name`, `modifier_name`)
VALUES
    (1825706731063808102, 'ROOT', '超级管理员', '超级管理员', 'true', 'root', 'root'),
    (1825706731063809202, 'ADMIN', '管理员', '管理员', 'false', 'root', 'root');

insert into `admin_account_admin_role_relation`
    (`admin_id`, `role_id`, `creator_name`, `modifier_name`)
values
    (1825706731063808302, 1825706731063808102, 'root', 'root');