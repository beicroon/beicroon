import axios from "axios";
import toast from "@/utils/toast.utils.ts";
import router from "@/utils/router.utils.ts";
import {BooleanEnums, CacheKeyEnums} from "@/enums/default-enums.ts";

const http = axios.create({
    timeout: 6000,
    baseURL: import.meta.env.VITE_REQUEST_URL,
    headers: {"Content-Type": "application/json"},
});

const minResponseTime = 380;

// 添加请求拦截器
http.interceptors.request.use(
    (config: any) => {
        config.headers["Authorization"] = localStorage.getItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

        config.metadata = {startTime: Date.now()};

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

                if (response.data.code === 401) {
                    localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_USER);
                    localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

                    await router.push("/login");
                }

                return reject(response.data);
            }

            const elapsedTime = Date.now() - response.config.metadata.startTime;

            if (elapsedTime < minResponseTime) {
                setTimeout(() => resolve(response.data), minResponseTime - elapsedTime);
            } else {
                return resolve(response.data);
            }
        })
    },
    async error => {
        console.error(error.response.data.message);

        await toast(`服务器错误[${error.response.data.code}]`, "error");

        return Promise.reject(error);
    }
);

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
