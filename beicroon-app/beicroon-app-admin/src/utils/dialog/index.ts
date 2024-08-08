import {createApp} from "vue";

import {AppSelectorEnums} from "@/enums/system.enums.ts";
import Dialog from "@/utils/dialog/components/Dialog.vue";

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

function complete(dialog: Node) {
    getEl().removeChild(dialog);

    if (getEl().childNodes.length <= 0) {
        getEl().style.display = "none";
    }
}

export default (message: string, confirm: () => Promise<void>) => {
    const app = createApp(Dialog, {
        title: "操作确认",
        message: message,
        confirm: confirm,
        complete: () => complete(dialog),
    });

    const dialog = app.mount(document.createElement("div")).$el;

    getEl().appendChild(dialog);

    getEl().style.display = "block";
}
