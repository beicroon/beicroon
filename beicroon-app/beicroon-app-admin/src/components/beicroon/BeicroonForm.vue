<script setup lang="ts">
import {ref} from "vue";
import toast from "@/utils/toast.utils.ts";
import {SelectorEnums} from "@/enums/default-enums.ts";

const emits = defineEmits(["submit"]);

const form  = ref();

async function handleSubmit() {
  const inputs = form.value.querySelectorAll(SelectorEnums.INPUT_REQUIRED);

  for (let i = 0; i < inputs.length; i++) {
    const input = inputs[i] as HTMLElement;

    const inputArea = input.querySelector(SelectorEnums.INPUT_AREA)  as HTMLInputElement;

    if (!inputArea.value) {
      await toast(`请填写必填项`, "error");

      inputArea.focus();

      return;
    }
  }

  emits("submit");
}
</script>

<template>
<form class="beicroon-form" @submit.prevent="handleSubmit" ref="form">
  <slot></slot>
</form>
</template>

<style lang="less">

</style>