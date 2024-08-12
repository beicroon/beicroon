import {Ref} from "vue";

const stacks: Record<string, (() => Promise<void>)[]> = {
    Enter: [],
    Escape: [],
};

document.addEventListener("keyup", async (e: KeyboardEvent) => {
    const callbacks = stacks[e.code];

    if (!Array.isArray(callbacks) || callbacks.length === 0) return;

    const callback = callbacks.pop();

    callback && await callback();
});

export function escUp(callback: () => Promise<void>) {
    stacks.Escape.push(callback);
}

export function validateForm(form: Ref<HTMLInputElement>) {
    let valid = true;

    const inputs: Element[] = form.value.querySelectorAll(".form-input.required");

    for (let i = 0; i < inputs.length; i++) {
        const elInput = inputs[i];

        const input: HTMLInputElement | null = elInput.querySelector(".input-value");

        if (input == null) {
            continue;
        }

        if (!input.value) {
            valid = false;

            elInput.classList.add("invalid");
        }
    }

    return valid;
}