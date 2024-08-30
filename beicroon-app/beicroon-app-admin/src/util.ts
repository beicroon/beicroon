const escStacks: (() => Promise<void>)[] = [];

async function escToggle(callback?: () => Promise<void>) {
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

export default escToggle;
