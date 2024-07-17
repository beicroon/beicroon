import axios from 'axios';
import {BooleanEnums} from '@/enums/system.enums.ts';

const http = axios.create({
    baseURL: import.meta.env.VITE_REQUEST_URL,
    timeout: 6000,
    headers: {'Content-Type': 'application/json'},
});

const minResponseTime = 3000;

// 添加请求拦截器
http.interceptors.request.use(
    (config: any) => {
        config.headers['Authorization'] = localStorage.getItem('Authorization');

        config.metadata = {startTime: Date.now()};

        if (config.method !== 'GET') {
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
    (response: any) => {
        const elapsedTime = Date.now() - response.config.metadata.startTime;

        if (elapsedTime < minResponseTime) {
            return new Promise((resolve) => {
                setTimeout(() => resolve(response.data), minResponseTime - elapsedTime);
            });
        } else {
            return response.data;
        }
    },
    error => {
        return Promise.reject(error);
    }
);

export default http;

export type PageInfo = {
    num: number,
    size: number,
    total: number,
}

export type Response<T> = {
    code: number,
    data: T,
    message: string,
    page: PageInfo,
    voice: string,
}

export type BaseVO = {
    id: string,
    createdAt: string,
    creatorId: string,
    creatorCode: string,
    creatorName: string,
    modifiedId: string,
    modifierId: string,
    modifierCode: string,
    modifierName: string,
}

export type DisableVO = {
    disabledFlag: BooleanEnums,
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
