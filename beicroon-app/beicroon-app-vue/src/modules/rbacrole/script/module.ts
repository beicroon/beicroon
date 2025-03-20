import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/utils";

export type QueryDTO = BeicroonQueryDTO & {
    code?: string;  // 编码
    name?: string;  // 名称
};

export type CreateDTO = {
    code?: string,  // 编码
    name?: string;  // 名称
    resourceIds?: Array<string>;  // 资源主键列表
};

export type UpdateDTO = {
    id?: string,  // 主键
    code?: string,  // 编码
    name?: string,  // 名称
    resourceIds?: Array<string>,  // 资源主键列表
};

export type BaseVO = BeicroonDisableVO & {
    code?: string;  // 编码
    name?: string;  // 名称
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {
    resourceIds?: Array<string>,  // 资源主键列表
};

const config = {
    tab: {
        url: "api/admin/rbac-role-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/rbac-role-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/rbac-role-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/rbac-role-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/rbac-role-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/rbac-role-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/rbac-role-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/rbac-role-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
