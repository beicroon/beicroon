import Main from "@/Main.vue";
import {App, createApp} from "vue";
import esc from "@/utils/utils.esc";
import toast from "@/utils/beicroon.toast";
import moduleApp from "@/utils/module.app";
import dialog from "@/utils/beicroon.dialog";
import {creteBeicroonRouter} from "@/router";
import overlay from "@/utils/beicroon.overlay";
import moduleCreate from "@/utils/module.create";
import moduleDetail from "@/utils/module.detail";
import moduleUpdate from "@/utils/module.update";
import {Router, RouteRecordRaw} from "vue-router";
import moduleRequest from "@/utils/module.request";
import http, {setRequestUrl} from "@/utils/utils.http";

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

export * from "@/utils/beicroon.dialog";
export * from "@/utils/beicroon.overlay";
export * from "@/utils/beicroon.toast";

export * from "@/utils/module.app";
export * from "@/utils/module.create";
export * from "@/utils/module.detail";
export * from "@/utils/module.request";
export * from "@/utils/module.update";

export * from "@/utils/utils.esc";
export * from "@/utils/utils.file";
export * from "@/utils/utils.function";
export * from "@/utils/utils.http";

export {
    dialog,
    esc,
    http,
    moduleApp,
    moduleCreate,
    moduleDetail,
    moduleRequest,
    moduleUpdate,
    overlay,
    toast,
};
