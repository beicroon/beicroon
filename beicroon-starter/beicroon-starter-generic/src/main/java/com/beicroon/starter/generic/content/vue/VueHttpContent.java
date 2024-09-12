package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueHttpContent {

    private static final String CONTENT = """
            import http, {BaseVO, DisableDTO, PageInfo, DisableVO, QueryDTO, Response, UpdateDTO} from "@/http";

            // 基础返回字段
            export type {{filename}}BaseVO = BaseVO & DisableVO & {
                {{content}}
            }

            // 详情返回字段
            export type {{filename}}DetailVO = {{filename}}BaseVO & {
            
            }

            // 分页返回字段
            export type {{filename}}PageVO = {{filename}}BaseVO & {
            
            }

            // 新增参数字段
            export type {{filename}}CreateDTO = DisableDTO & {
                {{content}}
            }

            // 修改参数字段
            export type {{filename}}UpdateDTO = UpdateDTO & {
                {{content}}
            }

            // 查询参数字段
            export type {{filename}}QueryDTO = QueryDTO & DisableDTO & {
                {{content}}
            }

            // 基础信息接口
            export async function show(id: String): Promise<Response<{{filename}}BaseVO>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-show?id=" + id,
                    method: "GET",
                })
            }

            // 详细信息接口
            export async function detail(id: String): Promise<Response<{{filename}}DetailVO>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-detail?id=" + id,
                    method: "GET",
                })
            }

            // 分页列表接口
            export async function page(data: {{filename}}QueryDTO, pageInfo: PageInfo): Promise<Response<Array<{{filename}}PageVO>>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-page",
                    method: "POST",
                    data: {
                        ...data,
                        pageNum: pageInfo.page,
                        pageSize: pageInfo.size,
                    },
                })
            }

            // 全量列表接口
            export async function list(data: {{filename}}QueryDTO): Promise<Response<Array<{{filename}}BaseVO>>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-list",
                    method: "POST",
                    data: data,
                })
            }

            // 单个新增接口
            export async function create(data: {{filename}}CreateDTO): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-create",
                    method: "POST",
                    data: data,
                })
            }

            // 单个修改接口
            export async function update(data: {{filename}}UpdateDTO): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-update",
                    method: "PUT",
                    data: data,
                })
            }

            // 删除接口
            export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
                return http.request({
                    url: "/api/{{modulePrefix}}/admin/{{urlName}}-remove",
                    method: "DELETE",
                    data: {ids: typeof ids === "string" ? [ids] : ids},
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
