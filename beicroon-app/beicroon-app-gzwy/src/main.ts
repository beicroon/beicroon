import "beicroon-app-vue/style.css";
import {banner} from "beicroon-app-vue/router";
import {createBeicroonApp} from "beicroon-app-vue";

const app = createBeicroonApp({
    requestUrl: import.meta.env.VITE_REQUEST_URL as string,
    baseURL: import.meta.env.VITE_BASE_URL as string,
    routes: [banner],
});

app.mount("#beicroon-app");
