import {createApp} from "vue";

import router from "@/router.ts";
import App from "@/apps/Main.vue";
import {AppSelectorEnums} from "@/enums/default-enums.ts";

const app = createApp(App)

app.use(router);

app.mount(AppSelectorEnums.APP);
