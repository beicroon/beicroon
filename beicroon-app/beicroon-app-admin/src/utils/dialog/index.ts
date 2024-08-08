import {createApp} from "vue";

import Dialog from "@/utils/dialog/components/Dialog.vue";

let el: HTMLElement | null = null;

function getEl() {
    if (el == null) {
        el = document.querySelector("#dialog");
    }

    if (el == null) {
        el = document.createElement("div");

        el.id = "dialog";

        document.body.appendChild(el);
    }

    return el;
}

export default (message: string, confirm: () => Promise<void>) => {
    const app = createApp(Dialog, {
        title: "操作确认",
        message: message,
        confirm: confirm,
        complete: () => {
            getEl().removeChild(dialog);
        },
    });

    const dialog = app.mount(document.createElement("div")).$el;

    getEl().appendChild(dialog);
}
