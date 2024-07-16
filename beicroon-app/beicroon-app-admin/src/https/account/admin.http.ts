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
        url: 'api/admin/admin/account-admin-show',
        method: 'GET',
        params: {id: id}
    })
}

export async function detail(id: String): Promise<Response<AdminDetailVO>> {
    return http.request({
        url: 'api/admin/admin/account-admin-detail',
        method: 'GET',
        params: {id: id}
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

