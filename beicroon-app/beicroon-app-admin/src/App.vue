<script setup lang="ts">
</script>

<template>
  <router-view></router-view>
</template>

<style lang="less">
* {
  margin: 0;
  padding: 0;
  box-shadow: none;
  box-sizing: border-box;
}

html {
  width: 100%;
  height: 100%;
  font-size: 1px;
}

body {
  z-index: 1;
  width: 100%;
  height: 100%;
  position: relative;
}

body, body a, input, textarea, button, select, option {
  color: #333333;
  font: 14rem/1.4 "PingFang SC", "Microsoft YaHei", Arial;
}

ul, ol, li {
  list-style: none;
}

input, textarea, button, select {
  border: none;
  outline: none;
  display: block;
  color: inherit;
  background: none;
  appearance: none;
  user-select: none;
  font-size: inherit;
}

button {
  cursor: pointer;
}

.list {
  padding: 8rem;
  width: max-content;
  border-radius: 8rem;
  border: 1rem solid #e0e4ed;

  .list-table {
    min-width: 100%;
    min-height: 100%;
    empty-cells: show;
    border-spacing: 0;
    border-collapse: collapse;
  }

  .list-head {
    z-index: 3;
    top: -9rem;
    position: sticky;
    background: #ecedef;
    border-top: 1rem solid #e0e4ed;

    .list-action {
      top: -9rem;
      background: #ecedef;
    }
  }

  .list-body {
    .list-row:nth-child(even) .list-cell {
      background: #ecedef;
    }
  }

  .list-cell {
    display: flex;
    min-width: 130rem;
    min-height: 60rem;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    border-left: 1rem solid #e0e4ed;
    border-bottom: 1rem solid #e0e4ed;
  }

  .list-action {
    z-index: 2;
    right: -9rem;
    position: sticky;
    background: #ffffff;
  }

  .list-button {
    padding: 0 18rem;
    flex-direction: row !important;
    border-right: 1rem solid #e0e4ed;
    justify-content: space-around !important;

    button {
      margin: 0 6rem;
    }
  }
}
</style>