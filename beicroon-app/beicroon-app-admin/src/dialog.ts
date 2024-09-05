import {h, render, VNode} from "vue";
import {escToggle} from "@/event.ts";
import {AppNameEnums} from "@/enums/default-enums.ts";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

const container: HTMLElement = document.createElement("div");

container.id = AppNameEnums.DIALOG;

container.classList.add("hidden");

document.body.appendChild(container);

function getNode() {
    const node = document.createElement("div")

    node.classList.add(AppNameEnums.DIALOG);

    node.addEventListener("click", (e: MouseEvent) => {
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();
    });

    return node;
}

type DialogConfig = {
    title?: string,
    message: string | VNode,
    cancel?: () => Promise<void>,
    confirm?: () => Promise<void>,
    finally?: (flag: boolean) => Promise<void>,
};

type Dialog = {
    title: string,
    message: string | VNode,
    handleCancel: () => Promise<void>,
    handleConfirm: () => Promise<void>,
};

function createMessageNode(config: Dialog) {
    const props: Record<string, any> = {
        class: "beicroon-dialog-message",
    };

    return h("div", props, [
        h("div", {class: "beicroon-dialog-title"}, config.title),
        h(BeicroonLineVertical),
        h("div", {class: "beicroon-dialog-content"}, config.message),
        h(BeicroonLineVertical),
        h("div", {class: "beicroon-dialog-button"}, [
            h(BeicroonButton, {
                class: ["block", "primary"],
                label: "取消",
                onClick: async () => {
                    await config.handleCancel();
                },
            }),
            h(BeicroonButton, {
                class: ["block", "warning"],
                label: "确认",
                onClick: async () => {
                    await config.handleConfirm();
                },
            }),
        ]),
    ]);
}

function createWindowNode(config: Dialog) {
    const props: Record<string, any> = {
        class: "beicroon-dialog-overlay",
    };

    return h("div", props, [
        h("div", {class: "beicroon-dialog-head"}, config.title),
        h(BeicroonLineVertical),
        h(config.message, {
            class: "beicroon-dialog-body",
            onCancel: async () => {
                await config.handleCancel();
            },
            onConfirm: async () => {
                await config.handleConfirm();
            },
        }),
    ]);
}

async function removeNode(node: HTMLElement) {
    container.removeChild(node);

    if (container.childNodes.length <= 0) {
        container.classList.add("hidden");
    }
}

export default async function dialog(config: DialogConfig) {
    const node = getNode();

    const isMessage = typeof config.message === "string";

    const dialog: Dialog = {
        title: config.title || "操作确认",
        message: config.message,
        handleCancel: async () => {
            await config.cancel?.();

            await removeNode(node);

            await config.finally?.(false);
        },
        handleConfirm: async () => {
            await config.confirm?.();

            await removeNode(node);

            await config.finally?.(true);
        },
    };

    if (isMessage) {
        const vNode = createMessageNode(dialog);

        render(vNode, node);
    } else {
        const vNode = createWindowNode(dialog);

        render(vNode, node);
    }

    container.appendChild(node);

    await escToggle(dialog.handleCancel);

    container.classList.remove("hidden");
}
