<script setup lang="ts">
</script>

<template>
  <router-view></router-view>
</template>

<style lang="less">
:root {
  color-scheme: light;
  /* 白色 */
  --color-white: #ffffff;
  --color-white-light: #f5f7fa;
  /* 灰色 */
  --color-grey: #909399;
  --color-grey-light: #c0c4cc;
  --color-grey-lighter: #eaeef3;
  --color-grey-deep: #606266;
  --color-grey-deeper: #303133;
  /* 黑色 */
  --color-black: #000000;
  --color-black-light: #333333;
  --color-black-lighter: #666666;
  --color-black-light-33: #99999933;
  --color-black-light-66: #99999966;
  /* 红色 */
  --color-danger: #f56c6c;
  --color-danger-light: #f78989;
  --color-danger-lighter: #fab6b6;
  --color-danger-deep: #dd6161;
  --color-danger-deeper: #c45656;
  /* 黄色 */
  --color-warning: #e6a23c;
  --color-warning-light: #ebb563;
  --color-warning-lighter: #f3d19e;
  --color-warning-deep: #cf9236;
  --color-warning-deeper: #b88230;
  /* 绿色 */
  --color-success: #67c23a;
  --color-success-light: #85ce61;
  --color-success-lighter: #b3e19d;
  --color-success-deep: #5daf34;
  --color-success-deeper: #529b2e;
  /* 蓝色 */
  --color-primary: #409eff;
  --color-primary-light: #66b1ff;
  --color-primary-lighter: #a0cfff;
  --color-primary-deep: #3a8ee6;
  --color-primary-deeper: #337ecc;
}

::-webkit-scrollbar {
  width: 6rem;
  height: 6rem;
}

::-webkit-scrollbar-track {
  border-radius: 4rem;
  background: transparent;
}

::-webkit-scrollbar-thumb {
  border-radius: 4rem;
  transition: all 200ms ease-in-out;
  background-color: var(--color-grey-light);
}

::-webkit-scrollbar-thumb:hover {
  background-color: var(--color-grey);
}

::-webkit-scrollbar-thumb:active {
  background-color: var(--color-grey);
}

* {
  cursor: inherit;
  box-sizing: border-box;
}

html {
  font-size: 1px;
}

body, body a {
  font-size: 14rem;
  line-height: 1.4;
  font-family: monospace;
  color: var(--color-black-light);
}

input, select, option {
  color: inherit;
  font-size: inherit;

  &::placeholder {
    letter-spacing: 1rem;
    color: var(--color-black-lighter);
  }
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  appearance: none;
}

ul, ol, li {
  margin: 0;
  padding: 0;
  list-style: none;
}

h1, h2, h3, h4, h5, h6 {
  margin: 0;
  padding: 0;
  font-size: 14rem;
}

button {
  margin: 0;
  padding: 0;
  border: none;
  outline: none;
  color: inherit;
  cursor: pointer;
  background: none;
  font-size: 14rem;
}

html, body, #beicroon-app {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  position: relative;
}

#beicroon-toast {
  top: 24%;
  left: 50%;
  gap: 4rem;
  display: grid;
  z-index: 8888;
  position: fixed;
  transform: translate(-50%, -50%);
  grid-template-columns: repeat(1, 1fr);

  .beicroon-toast-node {
    width: 360rem;
  }
}

#beicroon-dialog {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 7777;
  position: fixed;
  background-color: var(--color-black-light-33);

  .beicroon-dialog-node {
    top: 0;
    width: 100%;
    height: 100%;
    right: -100%;
    display: flex;
    position: absolute;
    align-items: center;
    justify-content: center;
  }
}

#beicroon-overlay {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 6666;
  position: fixed;
  background-color: var(--color-black-light-33);

  .beicroon-overlay-node {
    top: 0;
    width: 100%;
    height: 100%;
    right: -100%;
    display: flex;
    position: absolute;
    align-items: center;
    justify-content: center;
  }
}

.hidden {
  opacity: 0 !important;
  overflow: hidden !important;
  visibility: hidden !important;
}
</style>