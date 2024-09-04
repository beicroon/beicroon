import {createApp} from "vue";

import App from "@/Main.vue";
import router from "@/router.ts";
import {AppSelectorEnums} from "@/enums/system.enums.ts";

const app = createApp(App)

app.use(router);

app.mount(AppSelectorEnums.APP);
