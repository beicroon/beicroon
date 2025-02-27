import axios from "axios";
import toastUtils from "@u/toast.utils.ts";
import BeicroonCacheEnums from "@/enums/beicroon-cache-enums.ts";
import BeicroonBooleanEnums from "@/enums/beicroon-boolean-enums.ts";

const axiosInstance = axios.create({
    timeout: 6000,
    timeoutErrorMessage: "请求超时",
    baseURL: import.meta.env.VITE_REQUEST_URL,
    headers: {"Content-Type": "application/json"},
});

const minDelay = 320;

// 添加请求拦截器
axiosInstance.interceptors.request.use(
    (config: any) => {
        if (config.method !== "GET") {
            config.data = config.data || {};
        }

        config.meta = { startTime: Date.now() };

        config.headers["Authorization"] = localStorage.getItem(BeicroonCacheEnums.TOKEN);

        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// 添加响应拦截器
axiosInstance.interceptors.response.use(
    async (response: any) => {
        return new Promise(async (resolve, reject) => {
            if (response.data.code > 0) {
                reject(response.data);

                toastUtils.error(response.data.message);

                return;
            }

            const elapsedTime = Date.now() - response.config.meta.startTime;

            if (elapsedTime < minDelay) {
                setTimeout(() => resolve(response.data), minDelay - elapsedTime);

                return;
            }

            resolve(response.data);
        });
    },
    async error => {
        if (error.data && error.data.message) {
            toastUtils.error(error.data.message);
        } else if (error.message) {
            toastUtils.error(error.message);
        } else {
            toastUtils.error("服务异常!请稍后再试~");
        }

        return Promise.reject(error);
    }
);

const http = async <T>(config: BeicroonRequestConfig, data?: any): Promise<BeicroonResponse<T>> =>  {
    const requestConfig: Record<string, any> = {
        url: config.url,
        method: config.method,
    };

    if (data) {
        if (config.method === "GET") {
            requestConfig.params = data;
        } else {
            requestConfig.data = data;
        }
    }

    return axiosInstance.request(requestConfig);
}

export default http;

export type BeicroonRequestConfig = {
    url: string,
    method: "GET" | "POST" | "PUT" | "DELETE",
};

export type BeicroonResponse<T> = {
    code: number,
    message: string,
    data: T,
    pageInfo?: BeicroonPageInfo,
};

export type BeicroonTabVO = {
    code: string,
    name: string,
    sort: number,
    count: number,
    color: string,
};

export type BeicroonPageInfo = {
    page: number,
    size: number,
    total: number,
};

export type BeicroonQueryDTO = {
    keywords?: string,
    tabCode?: string,
    subTabCode?: string,
    id?: string,
    ids?: Array<string>,
    createdAt?: string,
    creatorId?: string,
    creatorName?: string,
    disabledFlag?: BeicroonBooleanEnums,
    pageInfo?: BeicroonPageInfo,
};

export type BeicroonGenericVO = {
    id: string,
    creatorId: string,
    createdAt: string,
    creatorCode: string,
    creatorName: string,
    modifiedAt: string,
    modifierId: string,
    modifierCode: string,
    modifierName: string,
};

export type BeicroonDisableVO = BeicroonGenericVO & {
    disabledAt: string,
    disabledId: string,
    disabledCode: string,
    disabledName: string,
};
