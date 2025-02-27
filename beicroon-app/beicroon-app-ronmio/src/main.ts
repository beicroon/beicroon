import App from "@/Main.vue";
import {createApp} from "vue";
import routes, {RouteMeta} from "@/routes.ts";
import {createRouter, createWebHistory} from "vue-router";
import BeicroonCacheEnums from "@/enums/beicroon-cache-enums.ts";

const app = createApp(App);

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes,
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

app.mount("#beicroon-app");
