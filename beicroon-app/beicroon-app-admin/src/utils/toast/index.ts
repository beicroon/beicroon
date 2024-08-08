import {createApp} from 'vue';

import Toast from '@/utils/toast/components/Toast.vue';
import Message from '@/utils/toast/components/Message.vue';

let toastEl: any = null;

function getToast() {
    if (toastEl == null) {
        toastEl = createApp(Toast).mount('#toast').$el;
    }

    return toastEl;
}

export default (message: string, error: boolean = false, duration: number = 3000) => {
    const app = createApp(Message, {
        message: message,
        error: error,
    });

    const messageEl = app.mount(document.createElement('div')).$el;

    getToast().prepend(messageEl);

    setTimeout(() => getToast().removeChild(messageEl), duration);
}
