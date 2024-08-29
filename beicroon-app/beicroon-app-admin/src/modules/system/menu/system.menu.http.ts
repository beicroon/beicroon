import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/http.ts";

// 基础返回字段
export type SystemMenuBaseVO = BaseVO & DisableVO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
    sorting?: number
}

// 详情返回字段
export type SystemMenuDetailVO = SystemMenuBaseVO & {

}

// 分页返回字段
export type SystemMenuPageVO = SystemMenuBaseVO & {

}

// 新增参数字段
export type SystemMenuCreateDTO = DisableDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
    sorting?: number
}

// 修改参数字段
export type SystemMenuUpdateDTO = UpdateDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
    sorting?: number
}

// 查询参数字段
export type SystemMenuQueryDTO = QueryDTO & DisableDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
    sorting?: number
}

// 基础信息接口
export async function show(id: String): Promise<Response<SystemMenuBaseVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-show?id=" + id,
        method: "GET",
    })
}

// 详细信息接口
export async function detail(id: String): Promise<Response<SystemMenuDetailVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-detail?id=" + id,
        method: "GET",
    })
}

// 分页列表接口
export async function page(data: SystemMenuQueryDTO): Promise<Response<Array<SystemMenuPageVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-page",
        method: "POST",
        data: data
    })
}

// 全量列表接口
export async function list(data: SystemMenuQueryDTO): Promise<Response<Array<SystemMenuBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-list",
        method: "POST",
        data: data
    })
}

// 单个新增接口
export async function create(data: SystemMenuCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-create",
        method: "POST",
        data: data
    })
}

// 单个修改接口
export async function update(data: SystemMenuUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-update",
        method: "PUT",
        data: data
    })
}

// 删除接口
export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids}
    })
}
