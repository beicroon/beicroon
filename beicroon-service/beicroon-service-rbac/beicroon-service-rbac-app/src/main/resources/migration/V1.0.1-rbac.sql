
INSERT INTO `rbac_account` (`id`, `tenant_id`, `code`, `name`, `username`, `password`, `phone`, `email`, `system_flag`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889976952042397698, 0, 'ROOT', '超级管理员', 'root', 'd35884e73e7437e418458db7e364b3d42c2c455921c4b821a131a0ec07fdb510', '', '', 'true', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_account` (`id`, `tenant_id`, `code`, `name`, `username`, `password`, `phone`, `email`, `system_flag`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889998545271496706, 0, 'ADMIN', '系统管理员', 'admin', 'f8f15bf3628017830ee4a666db5729a120f9534e7263820fe894817ef09190a2', '', '', 'true', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');

INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890009043349065730, 0, 1889998545271496706, 1889578431002775554, now(), 0, '', '', now(), 0, '', '', now(), 1889976952042397698, 'ROOT', '系统');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890009229186093057, 0, 1889998545271496706, 1889579017207091202, now(), 0, '', '', now(), 0, '', '', now(), 1889976952042397698, 'ROOT', '系统');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890009252334456834, 0, 1889976952042397698, 1889578431002775554, now(), 0, '', '', now(), 0, '', '', now(), 1889976952042397698, 'ROOT', '系统');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890243653061431297, 0, 1889998545271496706, 1889579017207091202, now(), 0, '', '', now(), 0, '', '', now(), 1889976952042397698, 'ROOT', '系统');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890243686724915201, 0, 1889976952042397698, 1889578431002775554, now(), 0, '', '', now(), 0, '', '', now(), 1889976952042397698, 'ROOT', '系统');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890247689651212290, 0, 1890247689626046466, 1890247310251249666, now(), 0, '', '', now(), 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563401593225218, 0, 1889998545271496706, 1889579017207091202, now(), 0, '', '', now(), 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_account_role_relation` (`id`, `tenant_id`, `account_id`, `role_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563418831814657, 0, 1889976952042397698, 1889578431002775554, now(), 0, '', '', now(), 0, '', '', NULL, 0, '', '');

INSERT INTO `rbac_resource` (`id`, `tenant_id`, `parent_id`, `code`, `name`, `path`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889569831266115585, 0, 0, 'SYSTEM_SETTING', '系统管理', '', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_resource` (`id`, `tenant_id`, `parent_id`, `code`, `name`, `path`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889576619650650114, 0, 1889569831266115585, 'BASIC_SETTING', '权限管理', '', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_resource` (`id`, `tenant_id`, `parent_id`, `code`, `name`, `path`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889576812370530306, 0, 1889576619650650114, 'RESOURCE_MANAGE', '资源管理', '/resource/app', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_resource` (`id`, `tenant_id`, `parent_id`, `code`, `name`, `path`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889576886769094657, 0, 1889576619650650114, 'ROLE_MANAGE', '角色管理', '/role/app', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_resource` (`id`, `tenant_id`, `parent_id`, `code`, `name`, `path`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889576975113719810, 0, 1889576619650650114, 'ACCOUNT_MANAGE', '账号管理', '/account/app', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');

INSERT INTO `rbac_role` (`id`, `tenant_id`, `code`, `name`, `system_flag`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889578431002775554, 0, 'ROOT', '系统', 'true', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');
INSERT INTO `rbac_role` (`id`, `tenant_id`, `code`, `name`, `system_flag`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `disabled_at`, `disabled_id`, `disabled_code`, `disabled_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1889579017207091202, 0, 'ADMIN', '系统管理员', 'true', now(), 0, 'system', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '', NULL, 0, '', '');

INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890247310251249667, 0, 1890247310251249666, 1889494418925867010, now(), 0, 'system', '系统', now(), 0, 'system', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890247310251249668, 0, 1890247310251249666, 1889570270191640578, now(), 0, 'system', '系统', now(), 0, 'system', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890247310251249669, 0, 1890247310251249666, 1889570614237814785, now(), 0, 'system', '系统', now(), 0, 'system', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1890247310251249670, 0, 1890247310251249666, 1889570736313032706, now(), 0, 'system', '系统', now(), 0, 'system', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563223100424193, 0, 1889579017207091202, 1889569831266115585, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563223100424194, 0, 1889579017207091202, 1889576619650650114, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563223100424195, 0, 1889579017207091202, 1889576886769094657, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563223100424196, 0, 1889579017207091202, 1889576975113719810, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563245506396161, 0, 1889578431002775554, 1889569831266115585, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563245506396162, 0, 1889578431002775554, 1889576619650650114, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563245506396163, 0, 1889578431002775554, 1889576812370530306, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563245506396164, 0, 1889578431002775554, 1889576886769094657, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
INSERT INTO `rbac_role_resource_relation` (`id`, `tenant_id`, `role_id`, `resource_id`, `created_at`, `creator_id`, `creator_code`, `creator_name`, `modified_at`, `modifier_id`, `modifier_code`, `modifier_name`, `removed_at`, `remover_id`, `remover_code`, `remover_name`) VALUES (1901563245506396165, 0, 1889578431002775554, 1889576975113719810, now(), 1889976952042397698, 'ROOT', '系统', now(), 1889976952042397698, 'ROOT', '系统', NULL, 0, '', '');
