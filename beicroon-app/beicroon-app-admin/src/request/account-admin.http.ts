import {sha256} from "@/utils/hash.utils.ts";
import http, {BaseVO, DisableDTO, DisableVO, PageInfo, QueryDTO, Response, UpdateDTO} from "@/utils/http.utils.ts";

// 基础返回字段
export type AccountAdminBaseVO = BaseVO & DisableVO & {
    code: string,  // 编码
    username: string,  // 账号
    nickname: string,  // 昵称
    phone: string,  // 电话
    email: string,  // 邮箱
}

// 详情返回字段
export type AccountAdminDetailVO = AccountAdminBaseVO & {

}

// 分页返回字段
export type AccountAdminPageVO = AccountAdminBaseVO & {

}

// 新增参数字段
export type AccountAdminCreateDTO = DisableDTO & {
    code?: string,  // 编码
    username?: string,  // 账号
    password?: string,  // 密码
    nickname?: string,  // 昵称
    phone?: string,  // 电话
    email?: string,  // 邮箱
}

// 修改参数字段
export type AccountAdminUpdateDTO = UpdateDTO & {
    code?: string,  // 编码
    username?: string,  // 账号
    password?: string,  // 密码
    nickname?: string,  // 昵称
    phone?: string,  // 电话
    email?: string,  // 邮箱
}

// 查询参数字段
export type AccountAdminQueryDTO = QueryDTO & DisableDTO & {
    code?: string,  // 编码
    username?: string,  // 账号
    nickname?: string,  // 昵称
    phone?: string,  // 电话
    email?: string,  // 邮箱
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
export async function page(dto: AccountAdminQueryDTO, pageInfo: PageInfo): Promise<Response<Array<AccountAdminPageVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-page",
        method: "POST",
        data: {
            ...dto,
            pageNum: pageInfo.page,
            pageSize: pageInfo.size,
        },
    })
}

// 全量列表接口
export async function list(dto: AccountAdminQueryDTO): Promise<Response<Array<AccountAdminBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-list",
        method: "POST",
        data: dto,
    })
}

// 单个新增接口
export async function create(dto: AccountAdminCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-create",
        method: "POST",
        data: {
            ...dto,
            password: await sha256(dto.password),
        },
    })
}

// 单个修改接口
export async function update(dto: AccountAdminUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-update",
        method: "PUT",
        data: {
            ...dto,
            password: await sha256(dto.password),
        },
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
