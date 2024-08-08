import http, {Response} from "@/https";


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

export async function login(data: AuthAdminLoginDTO): Promise<Response<AuthAdminLoginVO>> {
    return http.request({
        url: "api/admin/admin/auth-admin-login",
        method: "POST",
        data: data
    })
}