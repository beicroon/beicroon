import {reactive} from "vue";

const events = ["click", "mousedown", "mouseup", "keydown", "keyup"] as const;

type EventName = typeof events[number];

type Callback<E extends Event> = (e: E) => Promise<void>;

interface Stack<E extends Event> {
    el: Element;
    callback: Callback<E>;
}

type Stacks = {
    [K in EventName]: Stack<Event>[];
};

const stacks: Stacks = events.reduce(
    (acc, event: EventName) => {
        acc[event] = [];
        
        return acc;
    },
    {} as Stacks
);

const toggle = reactive({
    on<E extends Event>(event: EventName, el: Element, callback: Callback<E>) {
        stacks[event].push({el, callback: callback as Callback<Event>});
    },
    off<E extends Event>(event: EventName, el: Element, callback: Callback<E>) {
        const index = stacks[event].findIndex(
            (stack) => stack.el === el && stack.callback === callback
        );

        if (index !== -1) {
            stacks[event].splice(index, 1);
        }
    },
    pop(event: EventName) {
        return stacks[event].pop();
    },
});

events.forEach((eventName: EventName) => {
    document.addEventListener(eventName, async (e: Event) => {
        const stack = stacks[eventName][stacks[eventName].length - 1];

        if (stack && !stack.el.contains(e.target as Node)) {
            await stack.callback(e);

            toggle.off(eventName, stack.el, stack.callback);
        }
    });
});

export default toggle;