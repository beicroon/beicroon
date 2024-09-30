import {reactive} from "vue";
import routes, {Meta} from "@/routes.ts";
import toast from "@/utils/beicroon/toast.utils.ts";
import {createRouter, createWebHistory, Router} from "vue-router";
import {CacheKeyEnums, SystemEnums} from "@/enums/beicroon/beicroon-enums.ts";
import {AuthMenu, indexMenu, listAuthMenu, loginMenu} from "@/request/beicroon/account-admin-auth.http.ts";

export const router: Router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes,
});

const auth = reactive({
    ready: false,
    menus: [] as Array<AuthMenu>,
    prevMenu: null as AuthMenu | null,
    currentMenu: null as AuthMenu | null,
    links: [] as Array<AuthMenu>,
    paths: new Map<string, string>,
});

router.beforeEach(async (to, from, next) => {
    if (to.path === loginMenu.path) {
        return next();
    }

    const meta: Meta = to.meta as Meta;

    if (localStorage.getItem(CacheKeyEnums.AUTHORIZATION_USER) || (meta.auth && !meta.auth)) {
        if (auth.ready && !auth.paths.has(to.path)) {
            await toast("访问被拒绝！", "error");

            return next({path: indexMenu.path});
        }

        document.title = auth.paths.get(to.path) || SystemEnums.NAME;

        return next();
    }

    return next({path: loginMenu.path, query: {f: from.path, t: to.path}});
});

export async function toLinkPath(link: AuthMenu) {
    if (auth.prevMenu !== null) {
        auth.prevMenu.children.forEach((child) => {
            child.children.forEach((item) => {
                item.active = false;
            });
        });

        auth.prevMenu = null;
    }

    if (auth.currentMenu !== null) {
        auth.currentMenu.children.forEach((child) => {
            child.children.forEach((item) => {
                item.active = false;
            });
        });
    }

    link.active = true;

    await router.push(link.path);
}

export async function setLinks(menu: AuthMenu) {
    auth.menus.forEach((item) => item.active = false);

    auth.prevMenu = auth.currentMenu;

    auth.currentMenu = menu;

    menu.active = true;

    auth.links = menu.children;

    if ((!menu.children || menu.children.length == 0) && menu.path) {
        await router.push(menu.path);
    }
}

export async function clearAuth() {
    auth.ready = false;
    auth.menus = [] as Array<AuthMenu>;
    auth.prevMenu = null as AuthMenu | null;
    auth.currentMenu = null as AuthMenu | null;
    auth.links = [] as Array<AuthMenu>;
    auth.paths = new Map<string, string>;
}

export async function initMenus(path: string, reset: boolean = false) {
    if (auth.ready && !reset) {
        return;
    }

    const res = await listAuthMenu();

    auth.menus = [indexMenu].concat(res.data).map(menu => {
        addPath(menu.path, menu.name);

        menu.children = menu.children.map(child => {
            addPath(child.path, child.name);

            child.children = child.children.map(grand => {
                addPath(grand.path, grand.name);

                if (grand.path === router.currentRoute.value.path) {
                    setLinks(menu);

                    grand.active = true;
                }

                return grand;
            })

            return child;
        });

        return menu;
    });

    auth.ready = true;

    if (!auth.paths.has(path)) {
        await toast("访问被拒绝！", "error");

        await router.push({path: indexMenu.path});
    }
}

export async function addPath(path: string, name: string) {
    if (!path || !name) {
        return;
    }

    auth.paths.set(path, name);
}

export default auth;