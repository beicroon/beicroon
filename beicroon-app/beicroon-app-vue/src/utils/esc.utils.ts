type Callback = () => void;

const events: Array<Callback> = [];

document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
        callLast();
    }
});

export function push(callback: Callback) {
    events.push(callback);
}

export function pop () {
    events.pop();
}

export function callLast() {
    const event = events[events.length - 1];

    if (event) {
        event();
    }
}

export default {
    push,
    pop,
};
