<script setup lang="ts">
const emits = defineEmits(["submit"]);

async function handleSubmit() {
  emits("submit");
}
</script>

<template>
<form class="beicroon-form" @submit="handleSubmit">
  <slot></slot>
</form>
</template>

<style lang="less">

</style>