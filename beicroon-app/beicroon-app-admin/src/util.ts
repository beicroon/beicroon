const escStacks: Array<(() => Promise<void>)> = [];

export async function escToggle(callback?: () => Promise<void>) {
    if (callback) {
        await escToggle.put(callback);
    } else {
        await escToggle.pop();
    }
}

escToggle.put = async (callback: () => Promise<void>) => {
    escStacks.push(callback);
}

escToggle.pop = async () => {
    if (escStacks.length > 0) {
        const callback = escStacks.pop();

        if (callback) {
            await callback();
        }
    }
}

document.addEventListener("keyup", async (e) => {
    if (e.key === "Escape") {
        await escToggle.pop();
    }
});

document.addEventListener("click", async () => {
    await escToggle.pop();
});

const windowResizeStacks: Array<(() => Promise<void>)> = [];

export async function windowResize(callback?: () => Promise<void>) {
    if (callback) {
        await windowResize.put(callback);
    } else {
        await windowResize.pop();
    }
}

windowResize.put = async (callback: () => Promise<void>) => {
    windowResizeStacks.push(callback);
}

windowResize.pop = async () => {
    if (windowResizeStacks.length > 0) {
        const callback = windowResizeStacks.pop();

        if (callback) {
            await callback();
        }
    }
}

window.addEventListener("resize", () => windowResizeStacks.forEach(callback => callback()));

export default {escToggle, windowResize};
