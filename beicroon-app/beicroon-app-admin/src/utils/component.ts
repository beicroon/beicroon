import {App} from "vue";

export default {
    install(app: App) {
        app.component("el-button", import("@/components/elements/ElButton.vue"));
    }
}