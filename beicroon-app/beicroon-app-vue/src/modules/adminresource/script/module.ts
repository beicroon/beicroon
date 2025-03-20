import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/index";

export type QueryDTO = BeicroonQueryDTO & {
    parentId?: string;  // 父级主键
    code?: string;  // 编码
    name?: string;  // 名称
    path?: string;  // 路由
};

export type CreateDTO = {
    parentId?: string,  // 父级主键
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
};

export type UpdateDTO = {
    id?: string,  // 主键
    parentId?: string,  // 父级主键
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
};

export type BaseVO = BeicroonDisableVO & {
    parentId?: string;  // 父级主键
    parentCode?: string;  // 父级编码
    parentName?: string;  // 父级名称
    code?: string;  // 编码
    name?: string;  // 名称
    path?: string;  // 路由
};

export type TreeVO = BaseVO & {
    checked?: "checked" | "unchecked" | "fixed";
    children: TreeVO[];
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {

};

const config = {
    tab: {
        url: "api/admin/admin-resource-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/admin-resource-list",
        method: "POST",
    } as BeicroonRequestConfig,
    tree: {
        url: "api/admin/admin-resource-tree",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/admin-resource-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/admin-resource-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/admin-resource-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/admin-resource-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/admin-resource-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/admin-resource-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
