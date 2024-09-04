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
  --color-grey: #909399;
  --color-grey-light: #e9e9eb;
  --color-grey-lighter: #f4f4f5;
  --color-grey-deep: #b1b3b8;
  --color-grey-deeper: #c8c9cc;
  --color-white: #ffffff;
  --color-black: #000000;
  --color-black-light: #333333;
  --color-error: #f56c6c;
  --color-error-light: #fcd3d3;
  --color-error-lighter: #fde2e2;
  --color-error-deep: #f89898;
  --color-error-deeper: #fab6b6;
  --color-warning: #e6a23c;
  --color-warning-light: #f8e3c5;
  --color-warning-lighter: #faecd8;
  --color-warning-deep: #eebe77;
  --color-warning-deeper: #f3d19e;
  --color-success: #67c23a;
  --color-success-light: #d1edc4;
  --color-success-lighter: #e1f3d8;
  --color-success-deep: #95d475;
  --color-success-deeper: #b3e19d;
  --color-primary: #409eff;
  --color-primary-light: #c6e2ff;
  --color-primary-lighter: #d9ecff;
  --color-primary-deep: #a0cfff;
  --color-primary-deeper: #79bbff;
}
</style>