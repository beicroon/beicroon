import {h, render} from "vue";
import esc from "@u/esc.utils.ts";
import BeicroonDialog from "@u/components/BeicroonDialog.vue";

const getContainer = (): HTMLElement => {
    const container : HTMLElement | null = document.querySelector("#beicroon-dialog");

    if (container) {
        container.classList.add("hidden");

        return container;
    }

    const newContainer = document.createElement("div");

    newContainer.id = "beicroon-dialog";

    newContainer.classList.add("hidden");

    document.body.appendChild(newContainer);

    return newContainer;
};

const getNode = () => {
    const node = document.createElement("div");

    node.classList.add("beicroon-dialog-node");

    return node;
};

const remove = (node: HTMLElement) => {
    const animation = node.animate([
        {
            right: "0",
        },
        {
            right: "-100%",
        },
    ], {
        duration: 130,
        easing: "linear",
        fill: "forwards",
    });

    animation.onfinish = () => {
        node.remove();

        if (container.children.length === 0) {
            container.classList.add("hidden");
        }
    };
};

const container = getContainer();

export type BeicroonDialog = {
    title?: string,
    message: string,
    cancelLabel?: string,
    confirmLabel?: string,
    cancel?: () => Promise<void>,
    confirm?: () => Promise<void>,
    failed?: () => Promise<void>,
    succeed?: () => Promise<void>,
};

const dialog = (config: BeicroonDialog) => {
    const node = getNode();

    const cancel = async () => {
        await config.cancel?.();

        remove(node);

        await config.failed?.();

        esc.pop();
    };

    const confirm = async () => {
        await config.confirm?.();

        remove(node);

        await config.succeed?.();

        esc.pop();
    };

    const vNode = h(BeicroonDialog, {
        title: config.title || "操作提示",
        message: config.message,
        cancelLabel: config.cancelLabel || "取消",
        confirmLabel: config.confirmLabel || "确认",
        cancel: cancel,
        confirm: confirm,
    });

    render(vNode, node);

    container.appendChild(node);

    container.classList.remove("hidden");

    requestAnimationFrame(() => {
        const animation = node.animate([
            {
                right: "-100%",
            },
            {
                right: "0",
            },
        ], {
            duration: 130,
            easing: "linear",
            fill: "forwards",
        });

        animation.onfinish = () => esc.push(cancel);
    });
};

export default dialog;
