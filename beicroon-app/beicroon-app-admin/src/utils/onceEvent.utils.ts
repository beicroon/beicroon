const eventNames = ["click"] as const;

type EventName = typeof eventNames[number];

type Callback = (e?: UIEvent) => Promise<void>;

const eventStacks: Record<EventName, Array<Callback>> = {
    click: [],
};

export default async function onceEvent(event: EventName, callback: Callback | boolean = false, e?: UIEvent) {
    const callbacks = eventStacks[event];

    if (callback instanceof Function) {
        callbacks.push(callback);
    } else {
        const func = callbacks.pop();

        if (callback && func) {
            await func(e);
        }
    }
}

document.addEventListener("click", async (e: MouseEvent) => {
    const callbacks = eventStacks.click;

    if (callbacks) {
        const callback = callbacks.pop();

        if (callback) {
            await callback(e);
        }
    }
});
