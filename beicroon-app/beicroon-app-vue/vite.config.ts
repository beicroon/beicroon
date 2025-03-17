import {resolve} from "path";
import dts from "vite-plugin-dts";
import {defineConfig} from "vite";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
    plugins: [
        vue(),
        dts({
            insertTypesEntry: true,
        }),
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
    build: {
        lib: {
            entry: resolve(__dirname, "src/index.ts"),
            name: "BeicroonAppVue",
            fileName: (format) => `beicroon-app-vue.${format}.js`,
        },
        rollupOptions: {
            external: ["vue"],
            output: {
                exports: "named",
                globals: {
                    vue: "Vue"
                },
            },
        },
    },
    esbuild: {
        drop: process.env.NODE_ENV === "production" ? ["console", "debugger"] : [],
    },
});
