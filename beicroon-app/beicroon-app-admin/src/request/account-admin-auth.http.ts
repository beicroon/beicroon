import {sha256} from "@/utils/hash.utils.ts";
import http, {BaseVO, Response, waitingConfig} from "@/utils/http.utils.ts";

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

export type AuthMenu = BaseVO & {
    code: string;
    name: string;
    path: string;
    active: boolean;
    children: Array<AuthMenu>;
};

export const index: AuthMenu = {
    checked: "unchecked",
    id: "1",
    code: "INDEX",
    name: "首页",
    path: "/index",
    active: false,
    children: [] as Array<AuthMenu>,
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
