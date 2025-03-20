import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "@/index";

export type QueryDTO = BeicroonQueryDTO & {
    fileId?: string,  // 文件主键
    fileName?: string,  // 文件名称
    fileUrl?: string,  // 文件地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type CreateDTO = {
    fileId?: string,  // 文件主键
    fileName?: string,  // 文件名称
    fileUrl?: string,  // 文件地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type UpdateDTO = {
    id?: string,  // 主键
    fileId?: string,  // 文件主键
    fileName?: string,  // 文件名称
    fileUrl?: string,  // 文件地址
    description?: string,  // 描述
    validAt?: string,  // 生效时间
    expiredAt?: string,  // 过期时间
};

export type BaseVO = BeicroonDisableVO & {
    fileId?: string,  // 文件主键
    fileName?: string,  // 文件名称
    fileUrl?: string,  // 文件地址
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
        url: "api/admin/banner-banner-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/banner-banner-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/banner-banner-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/banner-banner-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/banner-banner-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/banner-banner-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/banner-banner-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/banner-banner-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
