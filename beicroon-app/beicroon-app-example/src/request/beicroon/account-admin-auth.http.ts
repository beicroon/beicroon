import {sha256} from "@/utils/beicroon/hash.utils.ts";
import http, {BaseVO, Response, waitingConfig} from "@/utils/beicroon/http.utils.ts";

export type LoginDTO = {
    username: string;
    password: string;
};

export type LoginVO = {
    code: string;
    name: string;
    token: string;
};

export type AuthUser = {
    code: string;
    name: string;
};

export async function login(dto: LoginDTO): Promise<Response<LoginVO>> {
    return http.request({
        url: "/api/admin/admin/auth-admin-login",
        method: "POST",
        data: {
            ...dto,
            password: await sha256(dto.password),
        },
    })
}

export async function logout(): Promise<Response<LoginVO>> {
    return http.request({
        url: "/api/admin/admin/auth-admin-logout",
        method: "POST",
    })
}

export type AuthMenu = BaseVO & {
    code: string;
    name: string;
    path: string;
    active: boolean;
    children: Array<AuthMenu>;
};

export async function listAuthMenu(): Promise<Response<Array<AuthMenu>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
    })
}

export async function listAuthMenuNoWaiting(): Promise<Response<Array<AuthMenu>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
        ...waitingConfig,
    })
}
