<script setup lang="ts">
defineProps({
  message: {
    type: String,
    required: true,
  },
  error: {
    type: Boolean,
    default: false,
    required: false,
  }
});
</script>

<template>
  <p class="message" :class="{error}">{{ message }}</p>
</template>

<style scoped lang="less">
.message {
  margin: 4rem;
  padding: 8rem;
  color: #28ab80;
  font-size: 16rem;
  font-weight: bold;
  text-align: center;
  background: #dbf6ed;
  border-radius: 8rem;
  border: 1px solid #b3edda;

  &.error {
    color: #f27979;
    background: #ffd2d2;
    border: 1px solid #f3b6b6;
  }
}
</style>