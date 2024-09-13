import http, {Response} from "@/http.ts";

export type LoginDTO = {
    username: string;
    password: string;
};

export type LoginVO = {
    code: string;
    nickname: string;
    token: string;
};

export type AuthUser = {
    code: string;
    nickname: string;
};

export function login(dto: LoginDTO): Promise<Response<LoginVO>> {
    return http.request({
        url: "/api/admin/admin/auth-admin-login",
        method: "POST",
        data: dto,
    })
}

export type AuthMenu = {
    code: string;
    name: string;
    path: string;
    active: boolean;
    children: Array<AuthMenu>;
};

export const index: AuthMenu = {
    code: "INDEX",
    name: "首页",
    path: "/index",
    active: false,
    children: [],
};

export function listAuthMenu(): Promise<Response<Array<AuthMenu>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
    })
}
