import http, {BaseVO, DisableDTO, DisableVO, QueryDTO, Response} from '@/https';

export type AdminBaseVO = BaseVO & DisableVO & {
    code: string,
    nickname: string,
    account: string,
    phone: string,
    email: string,
}

export type AdminDetailVO = AdminBaseVO & {}

export type AdminPageVO = AdminBaseVO & {}

export type AdminCreateDTO = QueryDTO & DisableDTO & {
    account?: string,
    nickname?: string,
    phone: string,
    email: string,
}

export type AdminUpdateDTO = QueryDTO & {
    nickname?: string,
    phone: string,
    email: string,
}

export type AdminQueryDTO = QueryDTO & DisableDTO & {
    code?: string,
    account?: string,
    nickname?: string,
    phone: string,
    email: string,
}

export async function show(id: String): Promise<Response<AdminBaseVO>> {
    return http.request({
        url: 'api/admin/admin/account-admin-show?id=' + id,
        method: 'GET',
    })
}

export async function detail(id: String): Promise<Response<AdminDetailVO>> {
    return http.request({
        url: 'api/admin/admin/account-admin-detail?id=' + id,
        method: 'GET',
    })
}

export async function list(data: AdminQueryDTO): Promise<Response<Array<AdminBaseVO>>> {
    return http.request({
        url: 'api/admin/admin/account-admin-list',
        method: 'POST',
        data: data
    })
}

export async function page(data: AdminQueryDTO): Promise<Response<Array<AdminPageVO>>> {
    return http.request({
        url: 'api/admin/admin/account-admin-page',
        method: 'POST',
        data: data
    })
}

export async function create(data: AdminCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: 'api/admin/admin/account-admin-create',
        method: 'POST',
        data: data
    })
}

export async function update(data: AdminUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: 'api/admin/admin/account-admin-update',
        method: 'PUT',
        data: data
    })
}

export async function enable(ids: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: 'api/admin/admin/account-admin-enable',
        method: 'PUT',
        data: {ids}
    })
}

export async function disable(ids: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: 'api/admin/admin/account-admin-disable',
        method: 'PUT',
        data: {ids}
    })
}

export async function remove(ids: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: 'api/admin/admin/account-admin-remove',
        method: 'DELETE',
        data: {ids}
    })
}

