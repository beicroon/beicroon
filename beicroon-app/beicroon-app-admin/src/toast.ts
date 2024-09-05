import {h, render} from "vue";
import {AppNameEnums} from "@/enums/default-enums.ts";

const container: HTMLElement = document.createElement("div");

container.id = AppNameEnums.TOAST;

document.body.appendChild(container);

function getNode() {
    const node = document.createElement("div")

    node.classList.add(AppNameEnums.TOAST);

    return node;
}

const types: Array<string> = ["success", "warning", "error"] as const;

type Types = typeof types[number];

export default async function toast(message: string, type: Types = "success", duration: number = 3000) {
    const node = getNode();

    const props: Record<string, any> = {
        class: ["beicroon-toast-message", type],
    };

    const vNode = h("span", props, message);

    render(vNode, node);

    container.appendChild(node);

    const remove = () => container.removeChild(node);

    setTimeout(remove, duration);
}
