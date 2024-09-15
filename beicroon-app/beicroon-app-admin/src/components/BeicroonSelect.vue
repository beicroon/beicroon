<script setup lang="ts">
import {computed, ref} from "vue";
import {Select} from "@/select.ts";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  select: Select<any, any>,
  label: string,
  showValue?: any,
  modelValue?: any,
  required?: boolean,
  disabled?: boolean,
  placeholder?: string,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:showValue", "update:modelValue"]);

const showValue = computed({
  get: () => props.showValue,
  set: async (val?: any) => {
    await props.select.search(val);

    emits("update:showValue", val);

    if (!val) {
      tempShowValue.value = null;

      emits("update:modelValue", null);
    }
  },
});

const chose = ref(false);

function choose(option: any) {
  chose.value = true;

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

const tempShowValue = ref(null);

function handleFocusin() {
  chose.value = false;

  tempShowValue.value = showValue.value;

  props.select.show();
}

function handleFocusout() {
  if (!chose.value && tempShowValue.value != showValue.value) {
    emits("update:showValue", tempShowValue.value);
  }

  tempShowValue.value = null;

  props.select.hide();
}

function handleMouseDown() {

}

function handleMouseUp() {

}
</script>

<template>
  <div class="beicroon-input beicroon-select"
       :class="{required: required}"
       @click.stop
       @mousedown="handleMouseDown"
       @mouseup="handleMouseUp"
  >
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area"
           type="text"
           :disabled="disabled"
           :placeholder="placeholder"
           v-model="showValue"
           @focusin="handleFocusin"
           @focusout="handleFocusout"
    />
    <ul class="select" :class="{hidden: select.hidden}" @scroll="loadMore" @click.stop>
      <li class="option" v-for="option in select.defaultOptions" @mousedown="choose(option)">
        {{ option[select.optionLabel] }}
      </li>
      <template v-for="options in select.options">
        <li class="option" v-for="option in options" @mousedown="choose(option)">
          {{ option[select.optionLabel] }}
        </li>
      </template>
      <li class="option loading" :class="{hidden: !select.loading}">
        <beicroon-loading fill="#b3e5fc" width="38" height="38"></beicroon-loading>
      </li>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-select {
  z-index: 666;
  position: relative;

  &.column {
    .select {
      right: 18rem;
      top: calc(100% - 12rem);
    }
  }

  .select {
    gap: 2rem;
    right: 6rem;
    z-index: 666;
    width: 300rem;
    display: flex;
    height: 160rem;
    padding: 8rem 0;
    overflow-y: auto;
    position: absolute;
    border-radius: 6rem;
    top: calc(100% - 4rem);
    flex-direction: column;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);

    .option {
      width: 100%;
      display: flex;
      height: 34rem;
      flex-shrink: 0;
      cursor: pointer;
      padding: 0 18rem;
      overflow: hidden;
      align-items: center;

      &:hover {
        background-color: var(--color-primary-light);
      }
    }

    .loading {
      justify-content: center;
    }
  }
}
</style>