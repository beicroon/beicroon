package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueModuleContent {

    private static final String CONTENT = """
            import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "beicroon-app-vue/utils";

            export type QueryDTO = BeicroonQueryDTO & {{{fieldContent}}
            };

            export type CreateDTO = {{{fieldContent}}
            };

            export type UpdateDTO = {
                id?: string,  // 主键{{fieldContent}}
            };

            export type BaseVO = BeicroonDisableVO & {{{fieldContent}}
            };

            export type PageVO = BaseVO & {

            };

            export type DetailVO = BaseVO & {

            };

            const config = {
                tab: {
                    url: "/api/admin/{{urlName}}-tab",
                    method: "POST",
                } as BeicroonRequestConfig,
                list: {
                    url: "/api/admin/{{urlName}}-list",
                    method: "POST",
                } as BeicroonRequestConfig,
                page: {
                    url: "/api/admin/{{urlName}}-page",
                    method: "POST",
                } as BeicroonRequestConfig,
                create: {
                    url: "/api/admin/{{urlName}}-create",
                    method: "POST",
                } as BeicroonRequestConfig,
                update: {
                    url: "/api/admin/{{urlName}}-update",
                    method: "PUT",
                } as BeicroonRequestConfig,
                show: {
                    url: "/api/admin/{{urlName}}-show",
                    method: "GET",
                } as BeicroonRequestConfig,
                detail: {
                    url: "/api/admin/{{urlName}}-detail",
                    method: "GET",
                } as BeicroonRequestConfig,
                remove: {
                    url: "/api/admin/{{urlName}}-remove",
                    method: "DELETE",
                } as BeicroonRequestConfig,
                disable: {
                    url: "/api/admin/{{urlName}}-disable",
                    method: "PUT",
                } as BeicroonRequestConfig,
                enable: {
                    url: "/api/admin/{{urlName}}-enable",
                    method: "PUT",
                } as BeicroonRequestConfig,
            };

            export default config;
            """;

    private VueModuleContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{fieldContent}}", table.getVueFieldContent())
                .replace("{{urlName}}", table.getUrlName())
                ;
    }

}
