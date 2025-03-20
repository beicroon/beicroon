import {App} from "vue";
import {BeicroonCacheEnums} from "@/enums";
import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";

export type RouteMeta = {
    auth: boolean,
};

export const login = {
    name: "登录",
    path: "/login",
    meta: {auth: false} as RouteMeta,
    component: () => import("@v/Login.vue"),
    children: [],
} as RouteRecordRaw;

export const index = {
    name: "index",
    path: "/",
    meta: {auth: true} as RouteMeta,
    component: () => import("@v/Index.vue"),
    children: [],
} as RouteRecordRaw;

export const home = {
    name: "home",
    path: "/",
    meta: {auth: true} as RouteMeta,
    component: () => import("@m/home/HomeApp.vue"),
    children: [],
} as RouteRecordRaw;

export const resource = {
    name: "资源管理",
    path: "/resource/app",
    meta: {auth: true} as RouteMeta,
    component: () => import("@m/rbacresource/RbacResourceApp.vue"),
    children: [],
} as RouteRecordRaw;

export const role = {
    name: "角色管理",
    path: "/role/app",
    meta: {auth: true} as RouteMeta,
    component: () => import("@m/rbacrole/RbacRoleApp.vue"),
    children: [],
} as RouteRecordRaw;

export const account = {
    name: "账号管理",
    path: "/account/app",
    meta: {auth: true} as RouteMeta,
    component: () => import("@m/rbacaccount/RbacAccountApp.vue"),
    children: [],
} as RouteRecordRaw;

export const banner = {
    name: "轮播图",
    path: "/banner/app",
    meta: {auth: true} as RouteMeta,
    component: () => import("@m/bannerbanner/BannerBannerApp.vue"),
    children: [],
} as RouteRecordRaw;

const creteBeicroonRouter = (app: App, baseURL: string, routes: RouteRecordRaw[]) => {
    index.children = [home, resource, role, account, ...routes];

    const router = createRouter({
        history: createWebHistory(baseURL),
        routes: [login, index],
    });

    router.beforeEach((to, _from, next) => {
        if (to.name) {
            document.title = to.name as string;
        }

        const meta = to.meta as RouteMeta;

        const token = localStorage.getItem(BeicroonCacheEnums.TOKEN);

        if (meta.auth && !token) {
            next({path: "/login"});

            return;
        }

        next();
    });

    app.use(router);
};

export {
    creteBeicroonRouter
};
