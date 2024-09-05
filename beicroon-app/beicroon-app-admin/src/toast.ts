import {h, render} from "vue";
import ToastMessage from "@/apps/ToastMessage.vue";
import {AppNameEnums} from "@/enums/default-enums.ts";

const container: HTMLElement = document.createElement("div");

container.id = AppNameEnums.TOAST;

container.classList.add("hidden");

document.body.appendChild(container);

function getNode() {
    const node = document.createElement("div");

    node.classList.add(AppNameEnums.TOAST);

    return node;
}

const types: Array<string> = ["success", "warning", "error"] as const;

type Types = typeof types[number];

async function handleRemove(node: HTMLElement) {
    if (!node) {
        return;
    }

    container.removeChild(node);

    if (container.childNodes.length <= 0) {
        container.classList.add("hidden");
    }
}

export default async function toast(message: string, type: Types = "success", duration: number = 3000) {
    const node = getNode();

    const vNode = h(ToastMessage, {
        class: type,
        message,
    });

    render(vNode, node);

    container.appendChild(node);

    container.classList.remove("hidden");

    setTimeout(() => handleRemove(node), duration);
}
