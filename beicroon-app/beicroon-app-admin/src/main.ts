import {createApp} from "vue";

import App from "@/apps/App.vue";
import router from "@/utils/router.utils.ts";
import {AppSelectorEnums} from "@/enums/default-enums.ts";

const app = createApp(App)

app.use(router);

app.mount(AppSelectorEnums.APP);
