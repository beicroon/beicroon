import {CacheKeyEnums} from '@/enums/system.enums.ts';
import {createRouter, createWebHistory} from 'vue-router';

type Meta = {
    auth: boolean,
}

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            name: '登录',
            path: '/login',
            meta: {auth: false} as Meta,
            component: () => import('@/views/Login.vue')
        },
        {
            name: '首页',
            path: '/',
            component: () => import('@/views/Index.vue'),
            children: [
                {
                    name: '后台账号',
                    path: '/account/admin',
                    component: () => import('@/views/index/account/admin/AccountAdminApp.vue'),
                },
                {
                    name: '用户账号',
                    path: '/account/user',
                    component: () => import('@/views/index/account/User.vue'),
                },
                {
                    name: '司机账号',
                    path: '/account/driver',
                    component: () => import('@/views/index/account/Driver.vue'),
                },
                {
                    name: '商户账号',
                    path: '/account/business',
                    component: () => import('@/views/index/account/Business.vue'),
                },
                {
                    name: '系统设置',
                    path: '/setting/system',
                    component: () => import('@/views/index/setting/System.vue'),
                },
                {
                    name: '角色管理',
                    path: '/setting/role',
                    component: () => import('@/views/index/setting/Role.vue'),
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

    if (to.path === '/login') {
        return next();
    }

    return next({path: '/login', query: {f: from.path, t: to.path}});
});

export default router;
