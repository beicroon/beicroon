<script setup>
import {defineComponent} from 'vue';

defineComponent({
  name: 'App',
});
</script>

<template>
  <router-view></router-view>
</template>

<style>
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
  font-size: inherit;
}

button {
  cursor: pointer;
}
</style>