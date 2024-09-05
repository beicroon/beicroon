<script setup lang="ts">
</script>

<template>
  <router-view></router-view>
</template>

<style lang="less">
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
  line-height: 1.4;
  color: var(--color-black-light);
  font-family: 'PingFang SC', 'Microsoft YaHei', monospace;
}

ul, ol, li {
  padding: 0;
  list-style: none;
}

html, body, #beicroon-app {
  z-index: 1;
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: relative;
}

&.hidden {
  display: none !important;
}

#beicroon-toast {
  top: 18%;
  gap: 8rem;
  left: 50%;
  z-index: 999;
  padding: 8rem;
  display: flex;
  position: fixed;
  min-width: 300rem;
  max-width: 500rem;
  align-items: center;
  justify-content: center;
  transform: translateX(-50%);
  flex-direction: column-reverse;
}

.beicroon-toast {
  width: 100%;

  .beicroon-toast-message {
    width: 100%;
    display: flex;
    padding: 8rem;
    border-radius: 6rem;
    align-items: center;
    justify-content: center;

    &.success {
      color: var(--color-success);
      background-color: var(--color-success-lighter);
      border: 1rem solid var(--color-success-deeper);
    }

    &.warning {
      color: var(--color-warning);
      background-color: var(--color-warning-lighter);
      border: 1rem solid var(--color-warning-deeper);
    }

    &.error {
      color: var(--color-error);
      background-color: var(--color-error-lighter);
      border: 1rem solid var(--color-error-deeper);
    }
  }
}

#beicroon-dialog {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 888;
  position: fixed;
  background-color: rgba(30, 30, 30, 0.3);
}

.beicroon-dialog {
  width: 100%;
  height: 100%;

  .beicroon-dialog-button {
    display: flex;
    align-items: center;
    justify-content: space-around;
  }
}
</style>