import {ref} from "vue";
import toast from "@/utils/beicroon.toast";
import axios, {AxiosInstance} from "axios";
import {BeicroonBooleanEnums, BeicroonCacheEnums} from "@/enums";

const baseURL = ref("");

export const setRequestUrl = (url: string) => {
    baseURL.value = url;
};

let axiosInstance: AxiosInstance | null = null;

const getAxiosInstance = () => {
    if (axiosInstance) {
        return axiosInstance;
    }

    const minDelay = 320;

    axiosInstance = axios.create({
        timeout: 6000,
        timeoutErrorMessage: "请求超时",
        baseURL: baseURL.value,
    });

    // 添加请求拦截器
    axiosInstance.interceptors.request.use(
        (config: any) => {

            if (config.method !== "GET") {
                config.data = config.data || {};
            }

            config.meta = {startTime: Date.now()};

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

                    toast.error(response.data.message);

                    if (response.data.code && response.data.code === 401) {
                        window.location.href = "/login";
                    }

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
            if (error.data) {
                if (error.data.message) {
                    toast.error(error.data.message);
                }

                if (error.data.code && error.data.code === 401) {
                    window.location.href = "/login";
                }
            } else if (error.message) {
                toast.error(error.message);
            } else {
                toast.error("服务异常!请稍后再试~");
            }

            return Promise.reject(error);
        }
    );

    return axiosInstance;
};

const http = async <T>(config: BeicroonRequestConfig, data?: any): Promise<BeicroonResponse<T>> => {
    const requestConfig: Record<string, any> = {
        url: config.url,
        method: config.method,
    };

    if (data) {
        if (data instanceof FormData) {
            requestConfig.headers = {"Content-Type": "multipart/form-data"};
        } else {
            requestConfig.headers = {"Content-Type": "application/json"};
        }

        if (config.method === "GET") {
            requestConfig.params = data;
        } else {
            requestConfig.data = data;
        }
    }

    return getAxiosInstance().request(requestConfig);
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
