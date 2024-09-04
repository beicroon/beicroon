// esc按键事件管理
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

// 可视区域大小变化事件管理
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

// 鼠标抬起事件管理
const mouseUpStacks: Array<(() => Promise<void>)> = [];

export async function mouseUp(callback?: () => Promise<void>) {
    if (callback) {
        await mouseUp.put(callback);
    } else {
        await mouseUp.pop();
    }
}

mouseUp.put = async (callback: () => Promise<void>) => {
    mouseUpStacks.push(callback);
}

mouseUp.pop = async () => {
    if (mouseUpStacks.length > 0) {
        const callback = mouseUpStacks.pop();

        if (callback) {
            await callback();
        }
    }
}

document.addEventListener("mouseup", async () => {
    await mouseUp.pop();
});

export default {escToggle, windowResize, mouseUp};
