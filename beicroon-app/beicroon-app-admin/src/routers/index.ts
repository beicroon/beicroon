import {CacheKeyEnums} from "@/enums/system.enums.ts";
import {createRouter, createWebHistory} from "vue-router";

type Meta = {
    auth: boolean,
}

const router = createRouter({
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
                    name: "资源管理",
                    path: "/setting/resource",
                    component: () => import("@/modules/resource/menu/ResourceMenuApp.vue"),
                },
            ],
        },
    ],
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

    if (token) {
        return next();
    }

    const meta: Meta = to.meta as Meta;

    if (meta.auth && !meta.auth) {
        return next();
    }

    if (to.path === "/login") {
        return next();
    }

    return next({path: "/login", query: {f: from.path, t: to.path}});
});

export default router;
