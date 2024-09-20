import http, {BaseVO, DisableVO, Response} from "@/http";

// 基础返回字段
export type ResourceMenuBaseVO = BaseVO & DisableVO & {
    parentId?: string,  // 父级主键
    parentCode?: string,  // 父级编码
    parentName?: string,  // 父级名称
    code?: string,  // 编码
    name?: string,  // 名称
    path?: string,  // 路由
    sorting?: number,  // 排序
}

// 获取菜单
export async function list(roleId: string): Promise<Response<Array<ResourceMenuBaseVO>>> {
    return http.request({
        url: "/api/admin/admin/resource-role-menu-list?roleId=" + roleId,
        method: "GET",
    })
}

// 分配菜单
export async function create(roleId: string, menuIds: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-role-menu-assign",
        method: "POST",
        data: {roleId, menuIds},
    })
}
