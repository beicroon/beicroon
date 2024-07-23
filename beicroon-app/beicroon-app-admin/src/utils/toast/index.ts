import {createApp} from 'vue';

import App from '@/apps/Toast.vue';

const app = createApp(App)

app.mount('#toast');

export default (message: string) => {
    console.info(message);
}
