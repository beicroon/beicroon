import {createApp} from "vue";

import Toast from "@/utils/toast/components/Toast.vue";
import Message from "@/utils/toast/components/Message.vue";

let el: any = null;

function getEl() {
    if (el == null) {
        el = createApp(Toast).mount("#toast").$el;
    }

    return el;
}

export default (message: string, error: boolean = false, duration: number = 3000) => {
    const app = createApp(Message, {
        message: message,
        error: error,
    });

    const messageEl = app.mount(document.createElement("div")).$el;

    getEl().prepend(messageEl);

    setTimeout(() => getEl().removeChild(messageEl), duration);
}
