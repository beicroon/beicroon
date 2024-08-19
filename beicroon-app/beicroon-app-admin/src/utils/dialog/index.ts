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

async function doComplete(node: Node) {
    getEl().removeChild(node);

    if (getEl().childNodes.length <= 0) {
        getEl().style.display = "none";
    }
}

export default async (message: string, confirm: () => Promise<any>, complete?: () => Promise<any>) => {
    const app = createApp(App, {
        title: "操作确认",
        message: message,
        confirm: confirm,
        complete: async (flag: boolean) => {
            await doComplete(node);

            if (flag && complete) {
                await complete();
            }
        },
    });

    const node = app.mount(document.createElement("div")).$el;

    getEl().appendChild(node);

    getEl().style.display = "block";
}
