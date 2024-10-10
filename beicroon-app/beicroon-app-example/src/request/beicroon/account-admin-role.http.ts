import http, {Response, waitingConfig} from "@/utils/beicroon/http.utils.ts";

// 获取菜单
export async function list(adminId: string): Promise<Response<Array<string>>> {
    return http.request({
        url: "/api/admin/admin/account-admin-role-id-list?adminId=" + adminId,
        method: "GET",
        ...waitingConfig,
    })
}

// 分配菜单
export async function assign(adminId: string, roleIds: Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/admin/admin/account-admin-role-assign",
        method: "POST",
        data: {adminId, roleIds},
    })
}
