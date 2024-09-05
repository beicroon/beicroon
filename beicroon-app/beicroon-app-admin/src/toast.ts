import {createVNode, render} from "vue";
import {AppEnums} from "@/enums/default-enums.ts";

const container: HTMLElement = document.createElement("div");

container.id = AppEnums.TOAST;

document.body.appendChild(container);

function getNode() {
    const node = document.createElement("div")

    node.classList.add(AppEnums.TOAST);

    return node;
}

const types: Array<string> = ["success", "warning", "error"] as const;

type Types = typeof types[number];

export default async function (message: string, type: Types = "success", duration: number = 3000) {
    const props: Record<string, any> = {
        class: ["beicroon-toast-message", type],
    };

    const vNode = createVNode("span", props, message);

    const node = getNode();

    render(vNode, node);

    container.appendChild(node);

    const remove = () => container.removeChild(node);

    setTimeout(remove, duration);
}
