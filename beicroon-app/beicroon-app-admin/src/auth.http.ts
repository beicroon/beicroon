import http, {Response} from "@/http.ts";

export type AuthMenu = {
    code: string;
    name: string;
    path: string;
    active: boolean;
    children: Array<AuthMenu>;
};

// 全量列表接口
export function listAuthMenu(): Promise<Response<Array<AuthMenu>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
    })
}
