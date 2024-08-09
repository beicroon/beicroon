import {createApp} from "vue";

import router from "@/routers";
import App from "@/apps/App.vue";
// import component from "@/utils/component";
import {AppSelectorEnums} from "@/enums/system.enums.ts";

const app = createApp(App)

app.use(router);
// app.use(component);

let el: HTMLElement | null = null;

function getEl() {
    if (el == null) {
        el = document.querySelector(AppSelectorEnums.APP);
    }

    if (el == null) {
        el = document.createElement("div");

        el.id = AppSelectorEnums.APP;

        document.body.appendChild(el);
    }

    return el;
}

getEl().appendChild(app.mount(document.createElement("div")).$el);
