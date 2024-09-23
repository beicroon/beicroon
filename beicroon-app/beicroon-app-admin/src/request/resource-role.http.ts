import http, {BaseVO, DisableDTO, DisableVO, PageInfo, QueryDTO, Response, UpdateDTO} from "@/utils/http.utils.ts";

// 基础返回字段
export type ResourceRoleBaseVO = BaseVO & DisableVO & {
    code: string,  // 编码
    name: string,  // 名称
    remark: string,  // 备注
}

// 详情返回字段
export type ResourceRoleDetailVO = ResourceRoleBaseVO & {

}

// 分页返回字段
export type ResourceRolePageVO = ResourceRoleBaseVO & {

}

// 新增参数字段
export type ResourceRoleCreateDTO = DisableDTO & {
    code?: string,  // 编码
    name?: string,  // 名称
    remark?: string,  // 备注
}

// 修改参数字段
export type ResourceRoleUpdateDTO = UpdateDTO & {
    code?: string,  // 编码
    name?: string,  // 名称
    remark?: string,  // 备注
}

// 查询参数字段
export type ResourceRoleQueryDTO = QueryDTO & DisableDTO & {
    code?: string,  // 编码
    name?: string,  // 名称
    remark?: string,  // 备注
}

// 基础信息接口
export async function show(id: String): Promise<Response<ResourceRoleBaseVO>> {
    return http.request({
        url: "/api/admin/admin/resource-role-show?id=" + id,
        method: "GET",
    })
}

// 详细信息接口
export async function detail(id: String): Promise<Response<ResourceRoleDetailVO>> {
    return http.request({
        url: "/api/admin/admin/resource-role-detail?id=" + id,
        method: "GET",
    })
}

// 分页列表接口
export async function page(dto: ResourceRoleQueryDTO, pageInfo: PageInfo): Promise<Response<Array<ResourceRolePageVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-role-page",
        method: "POST",
        data: {
            ...dto,
            pageNum: pageInfo.page,
            pageSize: pageInfo.size,
        },
    })
}

// 全量列表接口
export async function list(dto: ResourceRoleQueryDTO): Promise<Response<Array<ResourceRoleBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-role-list",
        method: "POST",
        data: dto,
    })
}

// 单个新增接口
export async function create(dto: ResourceRoleCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-role-create",
        method: "POST",
        data: dto,
    })
}

// 单个修改接口
export async function update(dto: ResourceRoleUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-role-update",
        method: "PUT",
        data: dto,
    })
}

// 删除接口
export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-role-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids},
    })
}
