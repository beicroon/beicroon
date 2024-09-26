import {reactive} from "vue";

const eventNames = ["click"] as const;

type EventName = typeof eventNames[number];

type Callback<E extends Event> = (e?: E) => Promise<void>;

type Stack<E extends Event> = {
    el: Element;
    callback: Callback<E>;
};

const stacks = {
    click: [] as Stack<MouseEvent>[],
};

const toggle = reactive({
    on: (event: EventName, el: Element, callback: Callback<any>) => {
        stacks[event].push({el, callback});
    },
    pop: (event: EventName) => {
        return stacks[event].pop();
    },
});

export default toggle;

document.addEventListener("click", async (e: MouseEvent) => {
    const stack = stacks.click[stacks.click.length - 1];

    if (stack) {
        const {el, callback} = stack;

        if (el === e.target) {
            return;
        }

        await callback(e);

        stacks.click.slice(stacks.click.indexOf(stack), 1);
    }
});
