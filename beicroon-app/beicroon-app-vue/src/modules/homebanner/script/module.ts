import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/index";

export type QueryDTO = BeicroonQueryDTO & {
    url?: string,  // 地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type CreateDTO = {
    fileId?: string,  // 文件主键
    url?: string,  // 地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type UpdateDTO = {
    id?: string,  // 主键
    fileId?: string,  // 文件主键
    url?: string,  // 地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type BaseVO = BeicroonDisableVO & {
    fileId?: string,  // 文件主键
    url?: string,  // 地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {

};

const config = {
    tab: {
        url: "api/admin/admin/home-banner-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/admin/home-banner-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/admin/home-banner-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/admin/home-banner-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/admin/home-banner-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/admin/home-banner-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/admin/home-banner-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/admin/home-banner-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
