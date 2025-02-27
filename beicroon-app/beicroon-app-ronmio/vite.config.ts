import {resolve} from "path";
import {defineConfig} from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
    ],
    resolve: {
        alias: {
            "@": resolve(__dirname, "src"),
            "@c": resolve(__dirname, "src/components"),
            "@m": resolve(__dirname, "src/modules"),
            "@u": resolve(__dirname, "src/utils"),
            "@v": resolve(__dirname, "src/views"),
        },
    },
    css: {
        preprocessorOptions: {
            less: {
                javascriptEnabled: true,
            },
        },
    },
});
