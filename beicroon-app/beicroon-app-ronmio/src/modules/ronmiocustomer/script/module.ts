import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "beicroon-app-vue";

export type QueryDTO = BeicroonQueryDTO & {
    name?: string;  // 客户名称
    phone?: string;  // 联系电话
};

export type CreateDTO = {
    name?: string,  // 客户名称
    phone?: string,  // 联系电话
};

export type UpdateDTO = {
    id?: string,  // 主键
    name?: string,  // 客户名称
    phone?: string,  // 联系电话
};

export type BaseVO = BeicroonDisableVO & {
    name?: string;  // 客户名称
    phone?: string;  // 联系电话
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {

};

const config = {
    tab: {
        url: "api/admin/admin/ronmio-customer-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/admin/ronmio-customer-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/admin/ronmio-customer-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/admin/ronmio-customer-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/admin/ronmio-customer-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/admin/ronmio-customer-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/admin/ronmio-customer-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/admin/ronmio-customer-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
};

export default config;
