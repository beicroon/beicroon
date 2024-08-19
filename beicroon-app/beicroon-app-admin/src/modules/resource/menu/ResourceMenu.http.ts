import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response, UpdateDTO} from "@/https";

export type ResourceMenuBaseVO = BaseVO & DisableVO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
}

export type ResourceMenuDetailVO = ResourceMenuBaseVO & {}

export type ResourceMenuPageVO = ResourceMenuBaseVO & {}

export type ResourceMenuCreateDTO = DisableDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
}

export type ResourceMenuUpdateDTO = UpdateDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
}

export type ResourceMenuQueryDTO = QueryDTO & DisableDTO & {
    parentId?: string
    parentCode?: string
    parentName?: string
    code?: string
    name?: string
    path?: string
}

export async function show(id: String): Promise<Response<ResourceMenuBaseVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-show?id=" + id,
        method: "GET",
    })
}

export async function detail(id: String): Promise<Response<ResourceMenuDetailVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-detail?id=" + id,
        method: "GET",
    })
}

export async function list(data: ResourceMenuQueryDTO): Promise<Response<Array<ResourceMenuBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-list",
        method: "POST",
        data: data
    })
}

export async function page(data: ResourceMenuQueryDTO): Promise<Response<Array<ResourceMenuPageVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-page",
        method: "POST",
        data: data
    })
}

export async function create(data: ResourceMenuCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-create",
        method: "POST",
        data: data
    })
}

export async function update(data: ResourceMenuUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-update",
        method: "PUT",
        data: data
    })
}

export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids}
    })
}
