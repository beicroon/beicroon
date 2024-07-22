<script setup lang="ts">
import {escUp} from '@/utils/function.ts';

const emits = defineEmits(['close']);

async function close() {
  emits('close');
}

escUp(close);
</script>

<template>
<div class="window">CREATE</div>
</template>

<style lang="less">

</style>