import http, {Response} from "@/http";

// 获取菜单
export async function list(roleId: string): Promise<Response<Array<string>>> {
    return http.request({
        url: "/api/admin/admin/resource-role-menu-id-list?roleId=" + roleId,
        method: "GET",
    })
}

// 分配菜单
export async function assign(roleId: string, menuIds: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/resource-role-menu-assign",
        method: "POST",
        data: {roleId, menuIds},
    })
}
