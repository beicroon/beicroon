import http, {BaseVO, DisableDTO, DisableVO, PageInfo, QueryDTO, Response, UpdateDTO} from "@/utils/http.ts";

// 基础返回字段
export type ResourceMenuBaseVO = BaseVO & DisableVO & {
    parentId: string,  // 父级主键
    parentCode: string,  // 父级编码
    parentName: string,  // 父级名称
    code: string,  // 编码
    name: string,  // 名称
    path: string,  // 路由
    sorting: number,  // 排序
}

// 详情返回字段
export type ResourceMenuDetailVO = ResourceMenuBaseVO & {

}

// 分页返回字段
export type ResourceMenuPageVO = ResourceMenuBaseVO & {

}

// 新增参数字段
export type ResourceMenuCreateDTO = DisableDTO & {
    parentId?: string,  // 父级主键
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
    sorting?: number,  // 排序
}

// 修改参数字段
export type ResourceMenuUpdateDTO = UpdateDTO & {
    parentId?: string,  // 父级主键
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
    sorting?: number,  // 排序
}

// 查询参数字段
export type ResourceMenuQueryDTO = QueryDTO & DisableDTO & {
    parentId?: string,  // 父级主键
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
    sorting?: number,  // 排序
}

// 基础信息接口
export async function show(id: String): Promise<Response<ResourceMenuBaseVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-show?id=" + id,
        method: "GET",
    })
}

// 详细信息接口
export async function detail(id: String): Promise<Response<ResourceMenuDetailVO>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-detail?id=" + id,
        method: "GET",
    })
}

// 分页列表接口
export async function page(dto: ResourceMenuQueryDTO, pageInfo: PageInfo): Promise<Response<Array<ResourceMenuPageVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-page",
        method: "POST",
        data: {
            ...dto,
            pageNum: pageInfo.page,
            pageSize: pageInfo.size,
        },
    })
}

// 全量列表接口
export async function list(dto: ResourceMenuQueryDTO): Promise<Response<Array<ResourceMenuBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-list",
        method: "POST",
        data: dto,
    })
}

// 单个新增接口
export async function create(dto: ResourceMenuCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-create",
        method: "POST",
        data: dto,
    })
}

// 单个修改接口
export async function update(dto: ResourceMenuUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-update",
        method: "PUT",
        data: dto,
    })
}

// 删除接口
export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-menu-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids},
    })
}
