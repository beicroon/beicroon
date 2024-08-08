import {createApp} from "vue";

import App from "@/utils/toast/components/Toast.vue";
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

function complete(toast: Node) {
    getEl().removeChild(toast);

    if (getEl().childNodes.length <= 0) {
        getEl().style.display = "none";
    }
}

export default (message: string, error: boolean = false, duration: number = 3000) => {
    const app = createApp(App, {
        message: message,
        error: error,
    });

    const toast = app.mount(document.createElement("div")).$el;

    getEl().prepend(toast);

    getEl().style.display = "block";

    setTimeout(() => complete(toast), duration);
}
