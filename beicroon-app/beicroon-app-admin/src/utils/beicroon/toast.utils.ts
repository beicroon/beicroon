import {h, render} from "vue";
import {AppNameEnums} from "@/enums/beicroon/beicroon-enums.ts";
import ToastMessage from "@/apps/beicroon/BeicroonToastMessage.vue";

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

    await hide(node);

    container.removeChild(node);

    if (container.children.length <= 0) {
        container.classList.add("hidden");
    }
}

function show(node: HTMLElement) {
    node.animate([
        {
            opacity: 0,
            transform: "scale(0)",
        },
        {
            opacity: 1,
            transform: "scale(1)",
        },
    ], {
        duration: 130,
        easing: "linear",
    });
}

function hide(node: HTMLElement) {
    return new Promise((resolve) => {
        node.animate([
            {
                opacity: 1,
                transform: "scale(1)",
            },
            {
                opacity: 0,
                transform: "scale(0)",
            },
        ], {
            duration: 130,
            easing: "linear",
        });

        setTimeout(resolve, 130);
    });
}

async function remove(node: HTMLElement, duration: number) {
    let timer = setTimeout(() => handleRemove(node), duration);

    node.addEventListener("mouseenter", () => clearTimeout(timer));

    node.addEventListener("mouseleave", () => {
        timer = setTimeout(() => handleRemove(node), duration);
    });
}

export default async function toast(message: string, type: Types = "success", duration: number = 3000) {
    const node = getNode();

    const vNode = h(ToastMessage, {
        class: type,
        message,
    });

    render(vNode, node);

    container.appendChild(node);

    show(node);

    container.classList.remove("hidden");

    await remove(node, duration);
}
