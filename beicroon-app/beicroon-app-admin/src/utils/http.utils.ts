import axios from "axios";
import toast from "@/utils/toast.utils.ts";
import {router} from "@/utils/auth.utils.ts";
import {loginMenu} from "@/request/account-admin-auth.http.ts";
import {BooleanEnums, CacheKeyEnums} from "@/enums/default-enums.ts";

const http = axios.create({
    timeout: 6000,
    timeoutErrorMessage: "请求超时",
    baseURL: import.meta.env.VITE_REQUEST_URL,
    headers: {"Content-Type": "application/json"},
});

const waiting = 380;

// 添加请求拦截器
http.interceptors.request.use(
    (config: any) => {
        config.headers["Authorization"] = localStorage.getItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

        config.beicroonStart = Date.now();

        config.beicroonWaiting = config.waiting !== false;

        if (config.method !== "GET") {
            config.data = config.data || {};
        }

        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// 添加响应拦截器
http.interceptors.response.use(
    async (response: any) => {
        return new Promise(async (resolve, reject) => {
            if (response.data.code > 0) {
                await toast(response.data.message, "error");

                if (response.data.code === 401 || response.data.code === 403) {
                    localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_USER);
                    localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

                    await router.push(loginMenu);
                }

                return reject(response.data);
            }

            const elapsed = Date.now() - response.config.beicroonStart;

            if (response.config.beicroonWaiting && elapsed < waiting) {
                setTimeout(() => resolve(response.data), waiting - elapsed);
            } else {
                return resolve(response.data);
            }
        })
    },
    async error => {
        if (error.response) {
            await toast(`服务器错误[${error.response.data.code}]`, "error");
        }

        return Promise.reject(error);
    }
);

export const waitingConfig = {
    waiting: false,
};

export async function batchRequest(requests: Array<() => Promise<Response<any>>>): Promise<any> {
    const start = Date.now();

    try {
        const results = await Promise.all(requests.map(request => {
            return new Promise((resolve, reject) => request()
                .then(res => resolve(res.data))
                .catch(reject));
        }));

        const elapsed = Date.now() - start;

        if (elapsed < waiting) {
            return new Promise(resolve => {
                setTimeout(() => resolve(results), waiting - elapsed);
            });
        } else {
            return results;
        }
    } catch (error) {
        return Promise.reject(error);
    }
}

export default http;

export type PageInfo = {
    page: number,
    size: number,
    total?: number,
}

export type Response<T> = {
    code: number,
    data: T,
    message: string,
    page?: PageInfo,
    voice: string,
}

export type BaseVO = {
    checked?: "checked" | "unchecked" | "indeterminate",
    id: string,
    createdAt?: string,
    creatorId?: string,
    creatorCode?: string,
    creatorName?: string,
    modifiedAt?: string,
    modifierId?: string,
    modifierCode?: string,
    modifierName?: string,
}

export type DisableVO = {
    disabledFlag: BooleanEnums,
}

export type ChildrenVO<T extends ChildrenVO<T>> = BaseVO & {
    children: Array<T>,
}

export type UpdateDTO = {
    id: string,
}

export type QueryDTO = {
    pageNum?: number,
    pageSize?: number,
    keywords?: string,
    tabCode?: string,
    subTabCode?: string,
    id?: string,
    ids?: Array<string>,
    createdAt?: string,
    creatorId?: string,
    creatorCode?: string,
    creatorName?: string,
}

export type DisableDTO = {
    disabledFlag?: BooleanEnums,
}
