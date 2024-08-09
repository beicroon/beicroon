import {createApp} from "vue";

import App from "@/utils/toast/App.vue";
import {AppSelectorEnums} from "@/enums/system.enums.ts";

let el: HTMLElement | null = null;

function getEl() {
    if (el == null) {
        el = document.querySelector(AppSelectorEnums.TOAST);
    }

    if (el == null) {
        el = document.createElement("div");

        el.id = AppSelectorEnums.TOAST;

        document.body.appendChild(el);
    }

    return el;
}

function complete(node: Node) {
    getEl().removeChild(node);

    if (getEl().childNodes.length <= 0) {
        getEl().style.display = "none";
    }
}

export default async (message: string, error: boolean = false, duration: number = 3000) => {
    const app = createApp(App, {
        message: message,
        error: error,
    });

    const node = app.mount(document.createElement("div")).$el;

    getEl().prepend(node);

    getEl().style.display = "block";

    setTimeout(() => complete(node), duration);
}
