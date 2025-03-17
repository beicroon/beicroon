import Main from "@/Main.vue";
import {App, createApp} from "vue";
import {RouteRecordRaw} from "vue-router";
import http, {setRequestUrl} from "@u/http.utils";
import dialog from "@u/dialog.utils";
import esc from "@u/esc.utils";
import moduleApp from "@u/module-app.utils";
import moduleCreate from "@u/module-create.utils";
import moduleDetail from "@u/module-detail.utils";
import moduleRequest from "@u/module-request.utils";
import moduleUpdate from "@u/module-update.utils";
import overlay from "@u/overlay.utils";
import toast from "@u/toast.utils";
import componentRegister from "@/component-register";
import routerRegister from "@/router-register";

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
