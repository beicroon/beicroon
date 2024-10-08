import {createApp} from "vue";

import App from "@/apps/beicroon/BeicroonApp.vue";
import {router} from "@/utils/beicroon/auth.utils.ts";
import {AppSelectorEnums} from "@/enums/beicroon/beicroon-enums.ts";

const app = createApp(App)

app.use(router);

app.mount(AppSelectorEnums.APP);
