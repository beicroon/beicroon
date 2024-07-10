import {createRouter, createWebHistory} from 'vue-router';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            name: '登录',
            path: '/login',
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
                    component: () => import('@/views/index/account/Admin.vue'),
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

export default router;
