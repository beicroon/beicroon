export type Meta = {
    auth: boolean,
}

export default [
    {
        name: "登录",
        path: "/login",
        meta: {auth: false} as Meta,
        component: () => import("@/views/beicroon/Login.vue")
    },
    {
        name: "首页",
        path: "/",
        component: () => import("@/views/beicroon/Index.vue"),
        children: [
            {
                name: "版本",
                path: "/version",
                component: () => import("@/views/beicroon/Version.vue"),
            },
            {
                name: "后台账号",
                path: "/account/admin",
                component: () => import("@/modules/beicroon/admin/AccountAdminApp.vue"),
            },
            {
                name: "菜单管理",
                path: "/resource/menu",
                component: () => import("@/modules/beicroon/menu/ResourceMenuApp.vue"),
            },
            {
                name: "角色管理",
                path: "/resource/role",
                component: () => import("@/modules/beicroon/role/ResourceRoleApp.vue"),
            },
        ],
    },
];