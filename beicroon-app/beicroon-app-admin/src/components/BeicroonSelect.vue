<script setup lang="ts">
import {Select} from "@/select.ts";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import {computed} from "vue";

type Props = {
  select: Select<any, any>,
  modelValue?: string,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get: () => props.select.showValue,
  set: (val?: string) => {
    emits("update:modelValue", val);
  },
});
</script>

<template>
  <div class="beicroon-input beicroon-select" :class="{required: select.required}" v-on="select.getEvents()">
    <span class="beicroon-input-label">{{ select.label }}</span>
    <input class="beicroon-input-area" :disabled="select.disabled" type="text" :placeholder="select.placeholder" v-model="value"/>
    <div class="loading" :class="{hidden: !select.loading}">
      <beicroon-loading fill="#b3e5fc" width="60" height="60"></beicroon-loading>
    </div>
    <ul class="select" :class="{hidden: select.hidden}">
      <li class="option" v-for="option in select.options" @click="select.choose(option)">
        {{ option[select.optionLabel] }}
      </li>
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