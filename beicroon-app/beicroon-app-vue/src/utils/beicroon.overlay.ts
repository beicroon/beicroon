import esc from "./utils.esc";
import {Component, h, ref, render, VNode} from "vue";
import BeicroonOverlay from "./components/BeicroonOverlay.vue";

const getContainer = (): HTMLElement => {
    const element: HTMLElement | null = document.querySelector("#beicroon-overlay");

    if (element) {
        element.classList.add("hidden");

        return element;
    }

    const newElement = document.createElement("div");

    newElement.id = "beicroon-overlay";

    newElement.classList.add("hidden");

    document.body.appendChild(newElement);

    return newElement;
};

const getNode = () => {
    const node = document.createElement("div");

    node.classList.add("beicroon-overlay-node");

    return node;
};

const remove = (node: HTMLElement) => {
    return new Promise<void>(resolve => {
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

            if (container.children.length <= 0) {
                container.classList.add("hidden");
            }

            resolve();
        };
    });
};

const container = getContainer();

export type BeicroonOverlay = {
    title: string,
    width?: string,
    component: VNode | Component,
    props?: Record<string, any>,
    confirm?: () => Promise<void>,
};

const overlay = (config: BeicroonOverlay) => {
    const node = getNode();

    const hide = async () => onHide(false);

    const onHide = async (flag: boolean) => {
        await remove(node)

        if (flag && config.confirm) {
            await config.confirm();
        }

        esc.pop();
    };

    const vNode = h(
        BeicroonOverlay,
        {
            title: config.title,
            width: config.width,
        },
        {
            default: () => [
                h(config.component, {
                    ...config.props,
                    onHide,
                }),
            ],
        }
    );

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

        animation.onfinish = () => {
            esc.push(hide);

            const target = ref<any>(null);

            node.addEventListener("mousedown", async (e: MouseEvent) => {
                target.value = e.target;
            });

            node.addEventListener("click", async (e: MouseEvent) => {
                if (e.target !== target.value) {
                    return;
                }

                await hide();
            });
        };
    });
};

export default overlay;
