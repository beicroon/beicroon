import {createApp} from 'vue';

import router from '@/routers';

import App from '@/App.vue';

const app = createApp(App)

app.use(router);

app.component('el-button', import('@/components/elements/ElButton.vue'));

app.mount('#app');