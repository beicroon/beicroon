const stacks: Record<string, (() => Promise<void>)[]> = {
    Enter: [],
    Escape: [],
};

document.addEventListener('keyup', async (e: KeyboardEvent) => {
    const callbacks = stacks[e.code];

    if (!Array.isArray(callbacks) || callbacks.length === 0) return;

    const callback = callbacks.pop();

    callback && await callback();
});

export function escUp(callback: () => Promise<void>) {
    stacks.Escape.push(callback);
}