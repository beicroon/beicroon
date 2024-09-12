<script setup lang="ts">
import {computed} from "vue";
import {Select} from "@/select.ts";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  select: Select<any, any>,
  showValue?: any,
  modelValue?: any,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:showValue", "update:modelValue"]);

const showValue = computed({
  get: () => props.showValue,
  set: async (val?: any) => {
    await props.select.search(val);

    emits("update:showValue", val);
  },
});

function choose(option: any) {
  props.select.hide();

  emits("update:showValue", props.select.getLabel(option));
  emits("update:modelValue", props.select.getValue(option));
}

function loadMore(e: Event) {
  const target = e.target as HTMLElement;

  if (target.scrollHeight - target.scrollTop === target.clientHeight) {
    props.select.loadMoreOptions();
  }
}
</script>

<template>
  <div class="beicroon-input beicroon-select"
       :class="{required: select.required}"
       v-on="select.getEvents()"
  >
    <span class="beicroon-input-label">{{ select.label }}</span>
    <input class="beicroon-input-area"
           type="text"
           :disabled="select.disabled"
           :placeholder="select.placeholder"
           v-model="showValue"
    />
    <div class="loading" :class="{hidden: !select.loading}">
      <beicroon-loading fill="#b3e5fc" width="60" height="60"></beicroon-loading>
    </div>
    <ul class="select" :class="{hidden: select.hidden}" @scrollend="loadMore">
      <template v-for="options in select.options">
        <li class="option" v-for="option in options" @mousedown="choose(option)">
          {{ option[select.optionLabel] }}
        </li>
      </template>
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
    top: calc(100% - 4rem);
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);
  }

  .loading {
    align-items: center;
    justify-content: center;
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