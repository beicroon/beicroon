import {RouteRecordRaw} from "vue-router";

export type RouteMeta = {
    auth: boolean,
};

export default [
    {
        name: "登录",
        path: "/login",
        meta: {auth: false} as RouteMeta,
        component: () => import("@v/Login.vue"),
        children: [],
    },
    {
        name: "index",
        path: "/",
        meta: {auth: true} as RouteMeta,
        component: () => import("@v/Index.vue"),
        children: [
            {
                name: "home",
                path: "/",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/home/HomeApp.vue"),
                children: [],
            },
            {
                name: "资源管理",
                path: "/resource/app",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/adminresource/AdminResourceApp.vue"),
                children: [],
            },
            {
                name: "角色管理",
                path: "/role/app",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/adminrole/AdminRoleApp.vue"),
                children: [],
            },
            {
                name: "账号管理",
                path: "/account/app",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/adminaccount/AdminAccountApp.vue"),
                children: [],
            },
            {
                name: "客户管理",
                path: "/customer/app",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/ronmiocustomer/RonmioCustomerApp.vue"),
                children: [],
            },
            {
                name: "箱单发票",
                path: "/packinglistinvoice/app",
                meta: {auth: true} as RouteMeta,
                component: () => import("@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceApp.vue"),
                children: [],
            },
        ],
    },
] as RouteRecordRaw[];