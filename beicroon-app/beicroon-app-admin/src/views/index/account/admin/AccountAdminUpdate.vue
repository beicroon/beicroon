<script setup lang="ts">
import {escUp} from '@/utils/function.ts';

const emits = defineEmits(['close']);

async function close() {
  emits('close');
}

escUp(close);
</script>

<template>
<div class="window">UPDATE</div>
</template>

<style scoped lang="less">

</style>