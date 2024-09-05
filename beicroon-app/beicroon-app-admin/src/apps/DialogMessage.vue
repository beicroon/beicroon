<script setup lang="ts">
import {ref} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

type Props = {
  title: string,
  message: string,
  cancel: () => Promise<void>,
  confirm: () => Promise<void>,
};

const props = defineProps<Props>();

const loading = ref(false);

async function handleConfirm() {
  loading.value = true;

  await props.confirm().finally(() => loading.value = false);
}
</script>

<template>
<div class="beicroon-dialog-message">
  <div class="beicroon-dialog-head">{{title}}</div>
  <beicroon-line-vertical></beicroon-line-vertical>
  <div class="beicroon-dialog-body">{{message}}</div>
  <beicroon-line-vertical></beicroon-line-vertical>
  <div class="beicroon-dialog-button">
    <beicroon-button class="block primary" label="取消" @click="cancel"></beicroon-button>
    <beicroon-button class="block warning" label="确认" @click="handleConfirm" :loading="loading"></beicroon-button>
  </div>
</div>
</template>

<style lang="less">
.beicroon-dialog-message {
  top: 24%;
  left: 50%;
  gap: 18rem;
  display: flex;
  width: 520rem;
  padding: 18rem 0;
  position: absolute;
  border-radius: 6rem;
  flex-direction: column;
  transform: translateX(-50%);
  background-color: var(--color-white);

  .beicroon-dialog-head {
    display: flex;
    font-weight: bold;
    align-items: center;
    letter-spacing: 4rem;
    justify-content: center;
  }

  .beicroon-dialog-body {
    display: flex;
    padding: 18rem 0;
    align-items: center;
    letter-spacing: 2rem;
    justify-content: center;
  }
}
</style>