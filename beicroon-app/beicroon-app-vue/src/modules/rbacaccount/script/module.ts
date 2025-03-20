import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/utils";

export type QueryDTO = BeicroonQueryDTO & {
    code?: string;  // 编码
    name?: string;  // 名称
    username?: string;  // 登录账号
    phone?: string;  // 联系电话
    email?: string;  // 邮箱地址
};

export type CreateDTO = {
    code?: string,  // 编码
    name?: string,  // 名称
    username?: string,  // 登录账号
    password?: string,  // 登录密码
    phone?: string,  // 联系电话
    email?: string,  // 邮箱地址
    roleIds?: Array<string>,  // 角色主键列表
};

export type UpdateDTO = {
    id?: string,  // 主键
    code?: string,  // 编码
    name?: string,  // 名称
    username?: string,  // 登录账号
    password?: string,  // 登录密码
    phone?: string,  // 联系电话
    email?: string,  // 邮箱地址
    roleIds?: Array<string>,  // 角色主键列表
};

export type BaseVO = BeicroonDisableVO & {
    code?: string,  // 编码
    name?: string,  // 名称
    username?: string,  // 登录账号
    phone?: string,  // 联系电话
    email?: string,  // 邮箱地址
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {
    roleIds?: Array<string>,  // 角色主键列表
};

const config = {
    tab: {
        url: "api/admin/rbac-account-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/rbac-account-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/rbac-account-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/rbac-account-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/rbac-account-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/rbac-account-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/rbac-account-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/rbac-account-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
