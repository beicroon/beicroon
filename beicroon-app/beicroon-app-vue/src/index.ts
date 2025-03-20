import Main from "@/Main.vue";
import {App, createApp} from "vue";
import {setRequestUrl} from "@/utils";
import {creteBeicroonRouter} from "@/router";
import {Router, RouteRecordRaw} from "vue-router";

interface Config {
    requestUrl: string,
    baseURL: string,
    router?: Router,
    routes?: RouteRecordRaw[],
}

export function createBeicroonApp({requestUrl, baseURL, router, routes}: Config): App {
    setRequestUrl(requestUrl);

    const app = createApp(Main);

    if (routes) {
        creteBeicroonRouter(app, baseURL, routes);
    } else if (router) {
        app.use(router);
    }

    return app;
}
