<script setup lang="ts">
import {ref} from "vue";
import {toast} from "@/utils";
import BeicroonFormLoading from "@/components/BeicroonFormLoading.vue";

interface Props {
  disabled?: boolean,
  loading?: boolean,
}

const props = defineProps<Props>();

const emits = defineEmits(["submit"]);

const form = ref<HTMLElement>();

const handleSubmit = (e: Event) => {
  e.preventDefault();

  if (props.disabled) {
    return;
  }

  if (form.value) {
    const beicroonInputs = form.value.querySelectorAll(".beicroon-input.required");

    for (let i = 0; i < beicroonInputs.length; i++) {
      const beicroonInput = beicroonInputs[i] as HTMLElement;

      const input: HTMLInputElement | null = beicroonInput.querySelector(".input");

      if (input && !input.value) {
        beicroonInput.classList.add("error");

        input.focus();

        toast.error("请填写必填项");

        return;
      }
    }
  }

  emits("submit");
};
</script>

<template>
  <form class="beicroon-form" @submit="handleSubmit" ref="form">
    <div class="beicroon-form-view">
      <beicroon-form-loading v-if="loading"/>
      <slot v-else></slot>
    </div>
    <div class="beicroon-form-button">
      <slot name="button"></slot>
    </div>
  </form>
</template>

<style lang="less">
.beicroon-form {
  width: 100%;
  height: 100%;

  .beicroon-form-view {
    gap: 24rem;
    display: flex;
    overflow-y: auto;
    padding: 24rem 8rem;
    align-items: center;
    flex-direction: column;
    height: calc(100% - 58rem);
  }

  .beicroon-form-loading {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .beicroon-form-button {
    height: 58rem;
    display: flex;
    align-items: center;
    justify-content: space-around;
    border-top: 1rem solid var(--color-grey-light);
  }
}
</style>