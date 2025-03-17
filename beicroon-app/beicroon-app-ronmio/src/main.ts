import {createBeicroonApp, RouteMeta} from "beicroon-app-vue";

import "beicroon-app-vue/style.css";

const app = createBeicroonApp({
    requestUrl: import.meta.env.VITE_REQUEST_URL as string,
    baseURL: import.meta.env.VITE_BASE_URL as string,
    routes: [
        {
            name: "客户管理",
            path: "/customer/app",
            meta: {auth: true} as RouteMeta,
            component: () => import("@m/ronmiocustomer/RonmioCustomerApp.vue"),
            children: [],
        },
        {
            name: "箱单发票",
            path: "/packinglistinvoice/app",
            meta: {auth: true} as RouteMeta,
            component: () => import("@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceApp.vue"),
            children: [],
        },
    ],
});

app.mount("#beicroon-app");
