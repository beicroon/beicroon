import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/index";

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
        url: "api/admin/admin/admin-role-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/admin/admin-role-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/admin/admin-role-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/admin/admin-role-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/admin/admin-role-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/admin/admin-role-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/admin/admin-role-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/admin/admin-role-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
