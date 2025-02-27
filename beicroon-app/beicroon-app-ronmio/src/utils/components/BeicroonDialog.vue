<script setup lang="ts">
import {ref} from "vue";
import BeicroonButton from "@c/BeicroonButton.vue";

type Props = {
  title: string,
  message: string,
  cancelLabel: string,
  confirmLabel: string,
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
  <div class="beicroon-dialog">
    <div class="beicroon-dialog-title">{{ title }}</div>
    <div class="beicroon-dialog-message">{{ message }}</div>
    <div class="beicroon-dialog-button">
      <beicroon-button :label="cancelLabel" @click="cancel"/>
      <beicroon-button level="warning" :label="confirmLabel" @click="handleConfirm" :loading="loading"/>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-dialog {
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
  border: 1rem solid var(--color-grey-light);

  .beicroon-dialog-title {
    display: flex;
    font-weight: bold;
    align-items: center;
    letter-spacing: 4rem;
    justify-content: center;
  }

  .beicroon-dialog-message {
    width: 80%;
    margin: auto;
    display: flex;
    padding: 52rem 0;
    align-items: center;
    letter-spacing: 2rem;
    justify-content: center;
    border-top: 1rem solid var(--color-grey-light);
    border-bottom: 1rem solid var(--color-grey-light);
  }

  .beicroon-dialog-button {
    width: 100%;
    display: flex;
    justify-content: space-around;
  }
}
</style>