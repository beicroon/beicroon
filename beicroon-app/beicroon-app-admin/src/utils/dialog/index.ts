import {createApp} from "vue";

import App from "@/utils/dialog/App.vue";
import {AppSelectorEnums} from "@/enums/system.enums.ts";

let el: HTMLElement | null = null;

function getEl() {
    if (el == null) {
        el = document.querySelector(AppSelectorEnums.DIALOG);
    }

    if (el == null) {
        el = document.createElement("div");

        el.id = AppSelectorEnums.DIALOG;

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

export default (message: string, confirm: () => Promise<Response<boolean>>) => {
    const app = createApp(App, {
        title: "操作确认",
        message: message,
        confirm: confirm,
        complete: () => complete(node),
    });

    const node = app.mount(document.createElement("div")).$el;

    getEl().appendChild(node);

    getEl().style.display = "block";
}
