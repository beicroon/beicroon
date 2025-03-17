import {createBeicroonApp, routes} from "beicroon-app-vue";

import "beicroon-app-vue/style.css";

const app = createBeicroonApp({
    requestUrl: import.meta.env.VITE_REQUEST_URL as string,
    baseURL: import.meta.env.VITE_BASE_URL as string,
    routes: routes,
});

app.mount("#beicroon-app");
