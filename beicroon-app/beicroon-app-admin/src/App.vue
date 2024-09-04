<script setup lang="ts">
</script>

<template>
  <router-view></router-view>
</template>

<style lang="less">
* {
  margin: 0;
  box-shadow: none;
  box-sizing: border-box;
}

html {
  font-size: 1px;
}

body, body a {
  font-size: 14rem;
  line-height: 24rem;
  color: var(--color-black-light);
  font-family: "JetBrains Mono", "Fira Code", "Source Code Pro", Consolas, "Courier New", monospace;
}

ul, ol, li {
  padding: 0;
  list-style: none;
}

html, body, #app {
  z-index: 1;
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: relative;
}

::-webkit-scrollbar {
  width: 8rem;
  height: 8rem;
}

::-webkit-scrollbar-track {
  border-radius: 6rem;
  background: transparent;
}

::-webkit-scrollbar-thumb {
  border-radius: 6rem;
  background: var(--color-grey-lighter);
}

::-webkit-scrollbar-thumb:hover {
  background: var(--color-grey-light);
}

:root {
  color-scheme: light;
  --color-grey: #545c64;
  --color-grey-light: #babdc4;
  --color-grey-lighter: #e4e7ed;
  --color-grey-lightest: #f7f7f7;
  --color-grey-deep: #9b9d9e;
  --color-white: #ffffff;
  --color-black: #000000;
  --color-black-light: #333333;
  --color-error: #f56c6c;
  --color-warning: #e6a23c;
  --color-warning-deep: #ffb200;
  --color-success: #67c23a;
  --color-primary: #409eff;
}
</style>