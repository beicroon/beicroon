import {CacheKeyEnums} from "@/enums/default-enums.ts";
import {createRouter, createWebHistory, Router} from "vue-router";

type Meta = {
    auth: boolean,
}

const router: Router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            name: "登录",
            path: "/login",
            meta: {auth: false} as Meta,
            component: () => import("@/views/Login.vue")
        },
        {
            name: "首页",
            path: "/",
            component: () => import("@/views/Index.vue"),
            children: [
                {
                    name: "后台账号",
                    path: "/account/admin",
                    component: () => import("@/modules/account/admin/AccountAdminApp.vue"),
                },
                {
                    name: "菜单管理",
                    path: "/resource/menu",
                    component: () => import("@/modules/resource/menu/ResourceMenuApp.vue"),
                },
            ],
        },
    ],
});

router.beforeEach((to, from, next) => {
    const meta: Meta = to.meta as Meta;

    if (localStorage.getItem(CacheKeyEnums.AUTHORIZATION_USER) || (meta.auth && !meta.auth) || to.path === "/login") {
        if (typeof to.name === "string") {
            document.title = to.name;
        }

        return next();
    }

    return next({path: "/login", query: {f: from.path, t: to.path}});
});

export default router;
