import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/https";

export type MenuBaseVO = BaseVO & DisableVO & {
    code?: string,
    username?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

export type MenuDetailVO = MenuBaseVO & {}

export type MenuPageVO = MenuBaseVO & {}

export type MenuCreateDTO = DisableDTO & {
    username?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

export type MenuUpdateDTO = UpdateDTO & {
    nickname?: string,
    phone?: string,
    email?: string,
}

export type MenuQueryDTO = QueryDTO & DisableDTO & {
    code?: string,
    username?: string,
    nickname?: string,
    phone?: string,
    email?: string,
}

export async function show(id: String): Promise<Response<MenuBaseVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-show?id=" + id,
        method: "GET",
    })
}

export async function detail(id: String): Promise<Response<MenuDetailVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-detail?id=" + id,
        method: "GET",
    })
}

export async function list(data: MenuQueryDTO): Promise<Response<Array<MenuBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-list",
        method: "POST",
        data: data
    })
}

export async function page(data: MenuQueryDTO): Promise<Response<Array<MenuPageVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-page",
        method: "POST",
        data: data
    })
}

export async function create(data: MenuCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-create",
        method: "POST",
        data: data
    })
}

export async function update(data: MenuUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-update",
        method: "PUT",
        data: data
    })
}

export async function enable(ids: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-enable",
        method: "PUT",
        data: {ids}
    })
}

export async function disable(ids: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-disable",
        method: "PUT",
        data: {ids}
    })
}

export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids}
    })
}

