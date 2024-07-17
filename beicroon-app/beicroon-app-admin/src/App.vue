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

  .list-head {
    gap: 18rem;
    width: 100%;
    display: flex;
    align-items: center;
    margin-bottom: 8rem;
    justify-content: space-between;

    &.flex-row {
      .head-action{
        width: 252rem;
        flex-direction: row;
      }
    }
  }

  .head-search {
    flex: 1;
    gap: 8rem;
    width: 88rem;
    display: flex;
    flex-wrap: wrap;
  }

  .head-input {
    display: flex;
    min-width: 300rem;
    align-items: center;
    justify-content: space-between;

    span {
      flex: 1;
      display: flex;
      padding: 0 8rem;
      align-items: center;
      justify-content: space-between;
    }

    i {
      font-style: normal;
    }

    input {
      width: 240rem;
      display: block;
      padding: 4rem 8rem;
      border-radius: 8rem;
      border: 1rem solid #a4a3a3;

      &:focus {
        border-color: #287bf1;
      }

      &::placeholder {
        color: #a4a3a3;
      }
    }
  }

  .head-action {
    gap: 4rem;
    width: 88rem;
    height: 100%;
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }

  .head-button {
    width: 80rem;
    color: #ffffff;
    padding: 6rem 0;
    background: #287bf1;
    border-radius: 8rem;
  }

  .list-table {
    min-width: 100%;
    min-height: 100%;
    empty-cells: show;
    border-spacing: 0;
    border-collapse: collapse;
  }

  .table-head {
    z-index: 3;
    top: -9rem;
    position: sticky;
    background: #ecedef;
    border-top: 1rem solid #e0e4ed;

    .table-action {
      top: -9rem;
      background: #ecedef;
    }
  }

  .table-body {
    .table-row:nth-child(even) .table-cell {
      background: #ecedef;
    }
  }

  .table-cell {
    display: flex;
    min-width: 130rem;
    min-height: 60rem;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    border-left: 1rem solid #e0e4ed;
    border-bottom: 1rem solid #e0e4ed;
  }

  .table-action {
    z-index: 2;
    right: -9rem;
    position: sticky;
    background: #ffffff;
  }

  .table-button {
    padding: 0 18rem;
    flex-direction: row !important;
    border-right: 1rem solid #e0e4ed;
    justify-content: space-around !important;

    button {
      gap: 6rem;
    }
  }
}
</style>