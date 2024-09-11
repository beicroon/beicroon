<script setup lang="ts">
import {computed, ref} from "vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  modelValue?: string,
  loading?: boolean,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get: () => props.modelValue,
  set: async (val: string | undefined) => {
    emits("update:modelValue", val);
  },
})

const hidden = ref(true);
</script>

<template>
  <div class="beicroon-input beicroon-select" :class="{required: required}" @click.stop>
    <span class="input-label">{{ label }}</span>
    <input class="input-area" :disabled="disabled" type="text" :placeholder="placeholder" v-model="value"/>
    <div class="loading" :class="{hidden: !loading}">
      <beicroon-loading fill="#b3e5fc" width="60" height="60"></beicroon-loading>
    </div>
    <ul class="select" :class="{hidden: hidden}">
      <li class="option">选项1</li>
      <li class="option">选项2</li>
      <li class="option">选项3</li>
      <li class="option">选项4</li>
      <li class="option">选项5</li>
      <li class="option">选项6</li>
      <li class="option">选项7</li>
      <li class="option">选项8</li>
      <li class="option">选项9</li>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-select {
  z-index: 666;
  position: relative;

  &.column {
    .select, .loading {
      right: 18rem;
      width: 300rem;
      top: calc(100% - 12rem);
    }
  }

  .loading, .select {
    right: 6rem;
    z-index: 777;
    display: flex;
    width: 240rem;
    height: 160rem;
    position: absolute;
    border-radius: 6rem;
    align-items: center;
    top: calc(100% - 4rem);
    justify-content: center;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);
  }

  .select {
    gap: 2rem;
    z-index: 666;
    padding: 8rem 0;
    overflow-y: auto;
    flex-direction: column;

    .option {
      width: 100%;
      display: flex;
      height: 34rem;
      flex-shrink: 0;
      cursor: pointer;
      padding: 0 18rem;
      align-items: center;

      &:hover {
        background-color: var(--color-primary-light);
      }
    }
  }
}
</style>