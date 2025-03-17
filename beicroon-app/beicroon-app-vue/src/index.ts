import Main from "@/Main.vue";
import esc from "@u/esc.utils";
import {App, createApp} from "vue";
import toast from "@u/toast.utils";
import dialog from "@u/dialog.utils";
import overlay from "@u/overlay.utils";
import {RouteRecordRaw} from "vue-router";
import moduleApp from "@u/module-app.utils";
import routerRegister from "@/router-register";
import http, {setRequestUrl} from "@u/http.utils";
import moduleCreate from "@u/module-create.utils";
import moduleDetail from "@u/module-detail.utils";
import moduleUpdate from "@u/module-update.utils";
import moduleRequest from "@u/module-request.utils";
import componentRegister from "@/component-register";

export * from "@/router-register";

interface Config {
    requestUrl: string;
    baseURL: string;
    routes: RouteRecordRaw[];
}

const createBeicroonApp = ({requestUrl, baseURL, routes}: Config): App => {
    setRequestUrl(requestUrl);

    const app = createApp(Main);

    componentRegister(app);

    routerRegister(app, baseURL, routes);

    return app;
};

export {
    createBeicroonApp,
};

export * from "@u/function.utils";
export * from "@u/dialog.utils";
export * from "@u/esc.utils";
export * from "@u/http.utils";
export * from "@u/module-app.utils";
export * from "@u/module-create.utils";
export * from "@u/module-detail.utils";
export * from "@u/module-request.utils";
export * from "@u/module-update.utils";
export * from "@u/overlay.utils";
export * from "@u/toast.utils";

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
