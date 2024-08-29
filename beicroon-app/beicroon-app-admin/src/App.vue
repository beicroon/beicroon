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

:root {
  color-scheme: light;
  --color-grey: #545c64;
  --color-grey-light: #babdc4;
  --color-grey-lighter: #e4e7ed;
  --color-grey-lightest: #ebeef5;
  --color-white: #ffffff;
  --color-black: #000000;
  --color-black-light: #333333;
  --color-error: #f56c6c;
  --color-warning: #e6a23c;
  --color-success: #67c23a;
  --color-primary: #409eff;
}
</style>