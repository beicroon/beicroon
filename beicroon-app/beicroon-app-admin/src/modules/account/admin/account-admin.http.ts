import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/http";

// 基础返回字段
export type AccountAdminBaseVO = BaseVO & DisableVO & {
    code?: string,
    username?: string,
    password?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

// 详情返回字段
export type AccountAdminDetailVO = AccountAdminBaseVO & {

}

// 分页返回字段
export type AccountAdminPageVO = AccountAdminBaseVO & {

}

// 新增参数字段
export type AccountAdminCreateDTO = DisableDTO & {
    code?: string,
    username?: string,
    password?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

// 修改参数字段
export type AccountAdminUpdateDTO = UpdateDTO & {
    code?: string,
    username?: string,
    password?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

// 查询参数字段
export type AccountAdminQueryDTO = QueryDTO & DisableDTO & {
    code?: string,
    username?: string,
    password?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

// 基础信息接口
export async function show(id: String): Promise<Response<AccountAdminBaseVO>> {
    return http.request({
        url: "/api/admin/admin/account-admin-show?id=" + id,
        method: "GET",
    })
}

// 详细信息接口
export async function detail(id: String): Promise<Response<AccountAdminDetailVO>> {
    return http.request({
        url: "/api/admin/admin/account-admin-detail?id=" + id,
        method: "GET",
    })
}

// 分页列表接口
export async function page(data: AccountAdminQueryDTO): Promise<Response<Array<AccountAdminPageVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-page",
        method: "POST",
        data: data,
    })
}

// 全量列表接口
export async function list(data: AccountAdminQueryDTO): Promise<Response<Array<AccountAdminBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-list",
        method: "POST",
        data: data,
    })
}

// 单个新增接口
export async function create(data: AccountAdminCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-create",
        method: "POST",
        data: data,
    })
}

// 单个修改接口
export async function update(data: AccountAdminUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-update",
        method: "PUT",
        data: data,
    })
}

// 删除接口
export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids},
    })
}
