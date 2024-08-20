import http, {Response} from "@/https";
import {ResourceMenuTreeVO} from "@/modules/resource/menu/ResourceMenu.http.ts";

export type AuthAdminLoginDTO = {
    username: string,
    password: string,
}

export type AuthAdminBaseVO = {
    id: string,
    code: string,
    nickname: string,
}

export type AuthAdminLoginVO = AuthAdminBaseVO & {
    token: string,
}

export type MenuTreeVO = {
    code: string
    name: string
    path: string
    active: boolean
    children: ResourceMenuTreeVO[]
}

export async function login(data: AuthAdminLoginDTO): Promise<Response<AuthAdminLoginVO>> {
    return http.request({
        url: "api/admin/admin/auth-admin-login",
        method: "POST",
        data: data
    })
}

export async function tree(): Promise<Response<Array<MenuTreeVO>>> {
    return http.request({
        url: "/api/admin/admin/auth-menu-tree",
        method: "GET",
    })
}