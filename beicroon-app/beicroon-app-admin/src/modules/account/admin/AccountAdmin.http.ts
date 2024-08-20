import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/https";

export type AccountAdminBaseVO = BaseVO & DisableVO & {
    code?: string
    username?: string
    password?: string
    nickname?: string
    phone?: string
    email?: string
}

export type AccountAdminDetailVO = AccountAdminBaseVO & {}

export type AccountAdminPageVO = AccountAdminBaseVO & {}

export type AccountAdminCreateDTO = DisableDTO & {
    code?: string
    username?: string
    password?: string
    nickname?: string
    phone?: string
    email?: string
}

export type AccountAdminUpdateDTO = UpdateDTO & {
    code?: string
    username?: string
    password?: string
    nickname?: string
    phone?: string
    email?: string
}

export type AccountAdminQueryDTO = QueryDTO & DisableDTO & {
    code?: string
    username?: string
    password?: string
    nickname?: string
    phone?: string
    email?: string
}

export async function show(id: String): Promise<Response<AccountAdminBaseVO>> {
    return http.request({
        url: "/api/admin/admin/account-admin-show?id=" + id,
        method: "GET",
    })
}

export async function detail(id: String): Promise<Response<AccountAdminDetailVO>> {
    return http.request({
        url: "/api/admin/admin/account-admin-detail?id=" + id,
        method: "GET",
    })
}

export async function page(data: AccountAdminQueryDTO): Promise<Response<Array<AccountAdminPageVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-page",
        method: "POST",
        data: data
    })
}

export async function list(data: AccountAdminQueryDTO): Promise<Response<Array<AccountAdminBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-list",
        method: "POST",
        data: data
    })
}

export async function create(data: AccountAdminCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-create",
        method: "POST",
        data: data
    })
}

export async function update(data: AccountAdminUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-update",
        method: "PUT",
        data: data
    })
}

export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids}
    })
}
