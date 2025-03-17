import {App} from "vue";
import BeicroonCacheEnums from "@/enums/beicroon-cache-enums";
import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";

export type RouteMeta = {
    auth: boolean,
};

const routerRegister = (app: App, baseURL: string, routes: RouteRecordRaw[]) => {
    const router = createRouter({
        history: createWebHistory(baseURL),
        routes: [
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
                    ...routes,
                ],
            },
        ],
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

export default routerRegister;
