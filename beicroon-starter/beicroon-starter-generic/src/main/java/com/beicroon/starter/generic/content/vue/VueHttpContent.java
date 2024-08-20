package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueHttpContent {

    private static final String CONTENT = """
            import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/https";

            export type {{filename}}BaseVO = BaseVO & DisableVO & {
                {{content}}
            }

            export type {{filename}}DetailVO = {{filename}}BaseVO & {}

            export type {{filename}}PageVO = {{filename}}BaseVO & {}

            export type {{filename}}CreateDTO = DisableDTO & {
                {{content}}
            }

            export type {{filename}}UpdateDTO = UpdateDTO & {
                {{content}}
            }

            export type {{filename}}QueryDTO = QueryDTO & DisableDTO & {
                {{content}}
            }

            export async function show(id: String): Promise<Response<{{filename}}BaseVO>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-show?id=" + id,
                    method: "GET",
                })
            }

            export async function detail(id: String): Promise<Response<{{filename}}DetailVO>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-detail?id=" + id,
                    method: "GET",
                })
            }

            export async function page(data: {{filename}}QueryDTO): Promise<Response<Array<{{filename}}PageVO>>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-page",
                    method: "POST",
                    data: data
                })
            }

            export async function list(data: {{filename}}QueryDTO): Promise<Response<Array<{{filename}}BaseVO>>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-list",
                    method: "POST",
                    data: data
                })
            }

            export async function create(data: {{filename}}CreateDTO): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-create",
                    method: "POST",
                    data: data
                })
            }

            export async function update(data: {{filename}}UpdateDTO): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-update",
                    method: "PUT",
                    data: data
                })
            }

            export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-remove",
                    method: "DELETE",
                    data: {ids: typeof ids === "string" ? [ids] : ids}
                })
            }
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{modulePrefix}}", table.getPrefix())
                .replace("{{filename}}", table.getFilename())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{content}}", table.getVueHttpContent().trim());
    }

}
