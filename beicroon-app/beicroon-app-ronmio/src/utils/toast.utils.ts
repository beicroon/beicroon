import {h, render} from "vue";
import BeicroonToast from "@u/components/BeicroonToast.vue";

const getContainer = (): HTMLElement => {
    const container : HTMLElement | null = document.querySelector("#beicroon-toast");

    if (container) {
        container.classList.add("hidden");

        return container;
    }

    const newContainer = document.createElement("div");

    newContainer.id = "beicroon-toast";

    newContainer.classList.add("hidden");

    document.body.appendChild(newContainer);

    return newContainer;
};

const getNode = () => {
    const node = document.createElement("div");

    node.classList.add("beicroon-toast-node");

    return node;
};

const handleRemove = (node: HTMLElement, callback?: () => Promise<void>) => {
    const animation = node.animate([
        {
            transform: "translateY(0)",
        },
        {
            transform: "translateY(-32rem)",
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

        callback && callback();
    };
};

const remove = (node: HTMLElement, duration: number = 3000, callback?: () => Promise<void>) => {
    let timerId: number;

    const removeAfterDuration = () => {
        const start = performance.now();

        const step = () => {
            const now = performance.now();

            if (now - start >= duration) {
                handleRemove(node, callback);
            } else {
                timerId = requestAnimationFrame(step);
            }
        };

        timerId = requestAnimationFrame(step);
    };

    removeAfterDuration();

    node.addEventListener("mouseenter", () => cancelAnimationFrame(timerId));
    node.addEventListener("mouseleave", removeAfterDuration);
};

const container = getContainer();

const types = ["info", "error", "success", "warning"] as const;

type Types = typeof types[number];

export type BeicroonToast = {
    type: Types,
    message: string,
    duration?: number,
    finally?: () => Promise<void>,
};

const toast = (config: BeicroonToast) => {
    const node = getNode();

    const vNode = h(BeicroonToast, {
        class: config.type,
        message: config.message,
    });

    render(vNode, node);

    container.appendChild(node);

    container.classList.remove("hidden");

    requestAnimationFrame(() => {
        node.animate([
            {
                transform: "translateY(-32rem)",
            },
            {
                transform: "translateY(0)",
            },
        ], {
            duration: 130,
            easing: "linear",
            fill: "forwards",
        });
    });

    remove(node, config.duration, config.finally);
};

toast.info = (message: string, callback?: () => Promise<void>) => toast({type: "info", message, finally: callback});
toast.error = (message: string, callback?: () => Promise<void>) => toast({type: "error", message, finally: callback});
toast.success = (message: string, callback?: () => Promise<void>) => toast({type: "success", message, finally: callback});
toast.warning = (message: string, callback?: () => Promise<void>) => toast({type: "warning", message, finally: callback});

export default toast as typeof toast & {
    info: (message: string, callback?: () => Promise<void>) => void,
    error: (message: string, callback?: () => Promise<void>) => void,
    success: (message: string, callback?: () => Promise<void>) => void,
    warning: (message: string, callback?: () => Promise<void>) => void,
};
