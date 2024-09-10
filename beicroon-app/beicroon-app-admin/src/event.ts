// esc按键事件管理
const escStacks: Array<(() => Promise<void>)> = [];

export async function escToggle(callback?: (() => Promise<void>) | boolean) {
    if (callback && typeof callback === "function") {
        await escToggle.put(callback);
    } else {
        await escToggle.pop(callback);
    }
}

escToggle.put = async (callback: () => Promise<void>) => {
    escStacks.push(callback);
}

escToggle.pop = async (flag: boolean = true) => {
    if (escStacks.length > 0) {
        const callback = escStacks.pop();

        if (flag && callback) {
            await callback();
        }
    }
}

document.addEventListener("keyup", async (e: KeyboardEvent) => {
    if (e.key === "Escape") {
        await escToggle.pop();
    }
});

document.addEventListener("click", async () => {
    await escToggle.pop();
});

// 可视区域大小变化事件管理
const windowResizeStacks: Array<((e: Event) => Promise<void>)> = [];

export async function windowResize(callback?: (e: Event) => Promise<void>) {
    if (callback) {
        await windowResize.put(callback);
    } else {
        await windowResize.pop();
    }
}

windowResize.put = async (callback: (e: Event) => Promise<void>) => {
    windowResizeStacks.push(callback);
}

windowResize.pop = async () => {
    if (windowResizeStacks.length > 0) {
        windowResizeStacks.pop();
    }
}

window.addEventListener("resize", (e: Event) => {
    windowResizeStacks.forEach(callback => callback(e));
});

// 鼠标抬起事件管理
const mouseUpStacks: Array<((e: MouseEvent) => Promise<void>)> = [];

export async function mouseUp(callback: (e: MouseEvent) => Promise<void>) {
    await mouseUp.put(callback);
}

mouseUp.put = async (callback: (e: MouseEvent) => Promise<void>) => {
    mouseUpStacks.push(callback);
}

mouseUp.pop = async (e: MouseEvent) => {
    if (mouseUpStacks.length > 0) {
        const callback = mouseUpStacks.pop();

        if (callback) {
            await callback(e);
        }
    }
}

document.addEventListener("mouseup", async (e: MouseEvent) => {
    await mouseUp.pop(e);
});

// 鼠标移动事件管理
const mouseMoveStacks: Array<((e: MouseEvent) => Promise<void>)> = [];

export async function mouseMove(callback?: (e: MouseEvent) => Promise<void>) {
    if (callback) {
        await mouseMove.put(callback);
    } else {
        await mouseMove.pop();
    }
}

mouseMove.put = async (callback: (e: MouseEvent) => Promise<void>) => {
    mouseMoveStacks.push(callback);
}

mouseMove.pop = async () => {
    if (mouseMoveStacks.length > 0) {
        mouseMoveStacks.pop();
    }
}

document.addEventListener("mousemove", async (e: MouseEvent) => {
    mouseMoveStacks.forEach(callback => callback(e));
});

export default {escToggle, windowResize, mouseUp, mouseMove};
