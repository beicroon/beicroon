import {createApp} from "vue";
import {escUp} from "@/utils/function.ts";
import App from "@/utils/dialogWindow/App.vue";
import {AppSelectorEnums} from "@/enums/system.enums.ts";

let el: HTMLElement | null = null;

function getEl() {
    if (el == null) {
        el = document.querySelector(AppSelectorEnums.WINDOW);

        if (el == null) {
            el = document.createElement("div");

            el.id = AppSelectorEnums.WINDOW;

            document.body.appendChild(el);
        }

        el.addEventListener('click', autoComplete)
    }

    return el;
}

function autoComplete() {
    const node = nodes.pop();

    if (node) {
        complete(node);
    }
}

const nodes: Element[] = [];

function complete(node: Element) {
    node.classList.remove("show");

    setTimeout(() => {
        getEl().removeChild(node);

        if (getEl().childNodes.length <= 0) {
            getEl().style.display = "none";
        }
    }, 300)
}

export default async (title: string, is: any, binds: Record<string, any> = {}, events: Record<string, () => Promise<void>> = {}) => {
    const app = createApp(App, {
        is: is,
        title: title,
        binds: binds,
        events: events,
        complete: () => complete(node),
    });

    const node: Element = app.mount(document.createElement("div")).$el;

    nodes.push(node);

    getEl().prepend(node);

    getEl().style.display = "block";

    setTimeout(() => {
        node.classList.add("show");

        escUp(async () => complete(node));
    });
}
