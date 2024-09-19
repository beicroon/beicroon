import {h, ref, render, VNode} from "vue";
import DialogMessage from "@/apps/DialogMessage.vue";
import DialogOverlay from "@/apps/DialogOverlay.vue";
import {AppNameEnums} from "@/enums/default-enums.ts";

const container: HTMLElement = document.createElement("div");

container.id = AppNameEnums.DIALOG;

container.classList.add("hidden");

document.body.appendChild(container);

function getNode() {
    const node = document.createElement("div");

    node.classList.add(AppNameEnums.DIALOG);

    return node;
}

type DialogConfig = {
    title?: string,
    message: string | VNode,
    props?: Record<string, any>,
    cancel?: () => Promise<void>,
    confirm?: () => Promise<void>,
    finally?: (flag: boolean) => Promise<void>,
};

type Dialog = {
    title: string,
    message: string | VNode,
    props?: Record<string, any>,
    handleCancel: () => Promise<void>,
    handleConfirm: () => Promise<void>,
};

function createMessageNode(config: Dialog) {
    return h(DialogMessage, {
        title: config.title,
        message: config.message as string,
        cancel: config.handleCancel,
        confirm: config.handleConfirm,
    });
}

function createWindowNode(config: Dialog) {
    return h(DialogOverlay, {title: config.title}, {
        default: () => [
            h(config.message, {
                ...config.props,
                onCancel: config.handleCancel,
                onConfirm: config.handleConfirm,
            }),
        ],
    });
}

async function removeNode(node: HTMLElement) {
    if (!node) {
        return;
    }

    await hide(node);

    container.removeChild(node);

    if (container.childNodes.length <= 0) {
        container.classList.add("hidden");
    }
}

function show(node: HTMLElement) {
    node.animate([
        {
            opacity: 0,
            transform: "translateX(100%)"
        },
        {
            opacity: 1,
            transform: "translateX(0)"
        },
    ], {
        duration: 130,
        easing: "linear",
    });
}

function hide(node: HTMLElement) {
    return new Promise<void>((resolve) => {
        node.animate([
            {
                opacity: 1,
                transform: "translateX(0)"
            },
            {
                opacity: 0,
                transform: "translateX(100%)"
            },
        ], {
            duration: 130,
            easing: "linear",
        });

        setTimeout(resolve, 130);
    });
}

const mousedownFlag = ref(false);
const mouseUpFlag = ref(false);

function terminateClick(node: HTMLElement, callback: (e: MouseEvent) => Promise<void>, escHandler: (e: KeyboardEvent) => Promise<void>) {
    node.addEventListener("mousedown", () => {
        mousedownFlag.value = true;
    });

    node.addEventListener("mouseup", () => {
        mouseUpFlag.value = true;
    });

    node.addEventListener("click", async (e: MouseEvent) => {
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();

        if (mousedownFlag.value && mouseUpFlag.value) {
            document.removeEventListener("keyup", escHandler);

            await callback(e);
        }

        mousedownFlag.value = false;
        mouseUpFlag.value = false;
    });
}

function stopClick(node: HTMLElement) {
    node.addEventListener("click", (e: MouseEvent) => {
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();
    });
}

export default async function dialog(config: DialogConfig) {
    const node = getNode();

    const isMessage = typeof config.message === "string";

    const dialog: Dialog = {
        title: config.title || "操作确认",
        message: config.message,
        props: config.props,
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

    const escHandler = async (e: KeyboardEvent) => {
        if (e.key === "Escape") {
            document.removeEventListener("keyup", escHandler);

            await dialog.handleCancel();
        }
    };

    if (isMessage) {
        const vNode = createMessageNode(dialog);

        render(vNode, node);

        stopClick(node);
    } else {
        const vNode = createWindowNode(dialog);

        render(vNode, node);

        terminateClick(node, dialog.handleCancel, escHandler);

        document.addEventListener("keyup", escHandler);
    }

    container.appendChild(node);

    show(node);

    container.classList.remove("hidden");
}
