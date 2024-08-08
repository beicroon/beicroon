import {createApp} from "vue";

import router from "@/routers";
import component from "@/utils/component";

import App from "@/apps/App.vue";

const app = createApp(App)

app.use(router);
app.use(component);

app.mount("#app");