import {sha256} from "@/hash.ts";
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

export type AuthMenu = {
    id: string;
    code: string;
    name: string;
    path: string;
    active: boolean;
    children: Array<AuthMenu>;
    checked?: "checked" | "fixed" | "unchecked";
};

export const index: AuthMenu = {
    id: "1",
    code: "INDEX",
    name: "首页",
    path: "/index",
    active: false,
    children: [],
};

export async function listAuthMenu(): Promise<Response<Array<AuthMenu>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
    })
}
